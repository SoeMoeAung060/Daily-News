package com.soe.dailynews.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.soe.dailynews.data.local.database.NewsDatabase
import com.soe.dailynews.data.local.entity.RemoteKeyEntity
import com.soe.dailynews.data.mapper.toArticle
import com.soe.dailynews.data.remote.api.NewsApi
import com.soe.dailynews.domain.model.Article
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.net.SocketTimeoutException


@OptIn(ExperimentalPagingApi::class)
class RemoteMediator(
    private val sources: String? = null,
    private val newsApi: NewsApi,
    private val newsDatabase: NewsDatabase
) : RemoteMediator<Int, Article>() {

    private val articleDao = newsDatabase.articleDao()
    private val remoteKeysDao = newsDatabase.remoteKeyDao()

    //LoadType: Determines whether the data should be refreshed (REFRESH),
    // prepended (PREPEND), or appended (APPEND) based on the user's scroll.
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                //If REFRESH, get the closest remote key and load the first page (or 1 if none).
                REFRESH -> {
                    1
                }

                //If PREPEND, fetch the previous page or signal the end of pagination.
                PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    Log.d("RemotePrevKey", "RemotePrevKey: ${remoteKey?.prevPage}")
                    val prevPage = remoteKey?.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    prevPage
                }


                //If APPEND, load the next page or signal the end of pagination.
                APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    Log.d("RemoteNextKey", "RemoteNextKey: ${remoteKey?.nextPage}")
                    val nextPage = remoteKey?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    nextPage
                }
            }

            //Fetch news from the API:
            val response = newsApi.getNews(
                page = currentPage,
                sources = sources!!,
            ).also {
                try {
                    newsApi.getNews(
                        page = currentPage,
                        sources = sources ?: "",
                    )

                }catch (e: Exception){
                    Log.d("BreakingNewsRemoteMediator", "BreakingNewsRemoteMediator: $e")
                }
            }



            // This is the list of articles returned by the API call, which presumably contains a list of Article objects.
            // If the list is empty, it means the API has no more articles to return,
            // indicating that the end of the available data (pagination) has been reached. The result (true or false) is stored in the endOfPaginationReached variable.
            val endOfPaginationReached = response.articles.isEmpty()

            //Transaction block: If REFRESH, clear existing articles and remote keys from the database.
            // Then, insert the new articles and remote keys.

            newsDatabase.withTransaction {
                if (loadType == REFRESH) {
                    articleDao.deleteAll()
                    remoteKeysDao.deleteAllRemoteKeys()
                }

                //// Insert fetched articles to the database
                val articles = response.toArticle()


                Log.d("BreakingNewsRemoteMediator", "BreakingNews articles: ${articles.size}")
                val prevKey = if (currentPage == 1) null else currentPage - 1
                val nextKey = if (endOfPaginationReached) null else currentPage + 1

                Log.d("PrevKey", "prevKey : $prevKey")
                Log.d("nextKey", "nextKey : $nextKey")

                // Insert remote keys to the database
                val keys = response.articles.map {
                    RemoteKeyEntity(
                        url = it.url,
                        prevPage = prevKey,
                        nextPage = nextKey
                    )
                }
                // Insert articles and remote keys to the database
                remoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                // Insert articles to the database
                articleDao.insertAll(articles)
                Log.d("BreakingNewsRemoteMediator", "BreakingNews keys: ${keys.size}")

            }

            // Return MediatorResult.Success if successful, or
            // MediatorResult.Error if an error occurs (like SocketTimeoutException, HttpException).
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (e: SocketTimeoutException) {
            MediatorResult.Error(Throwable("Network Timeout. Please Try Again."))
        } catch (e: HttpException) {
            if (e.code() == 429) { // Retry-After handling
                val retryAfter = e.response()?.headers()?.get("Retry-After")?.toIntOrNull() ?: 60
                delay(retryAfter * 1000L)
            }
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Article>
    ): RemoteKeyEntity? {

        return state.pages.firstOrNull() { it.data.isNotEmpty()}?.data?.firstOrNull()
            .let { article ->
                remoteKeysDao.getRemoteKeys(article?.url ?: "")
            }

    }


    private fun getRemoteKeyForLastItem(
        state: PagingState<Int, Article>
    ): RemoteKeyEntity? {

        return state.pages.lastOrNull() { it.data.isNotEmpty()}?.data?.lastOrNull()
            .let { article ->
                remoteKeysDao.getRemoteKeys(article?.url ?: "")
            }

    }


}