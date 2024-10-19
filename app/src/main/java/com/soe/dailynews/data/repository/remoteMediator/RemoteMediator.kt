package com.soe.dailynews.data.repository.remoteMediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.soe.dailynews.data.local.database.NewsDatabase
import com.soe.dailynews.data.local.entity.BreakingNewsRemoteKeyEntity
import com.soe.dailynews.data.mapper.toArticle
import com.soe.dailynews.data.remote.api.NewsApi
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.util.PER_PAGE_SIZE
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.net.SocketTimeoutException


@OptIn(ExperimentalPagingApi::class)
class RemoteMediator(
    private val country: String? = null,
    private val newsApi: NewsApi,
    private val sources: String? = null,
    private val newsDatabase: NewsDatabase
) : RemoteMediator<Int, Article>() {

    private val articleDao = newsDatabase.articleDao()
    private val breakingNewsRemoteKeysDao = newsDatabase.breakingNewsRemoteKeysDao()

    //LoadType: Determines whether the data should be refreshed (REFRESH),
    // prepended (PREPEND), or appended (APPEND) based on the user's scroll.
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                //If REFRESH, get the closest remote key and load the first page (or 1 if none).
                LoadType.REFRESH -> getRemoteKeyClosestToCurrentPosition(state)?.nextPage?.minus(1)
                    ?: 1

                //If PREPEND, fetch the previous page or signal the end of pagination.
                LoadType.PREPEND -> getRemoteKeyForFirstItem(state)?.prevPage
                    ?: return MediatorResult.Success(endOfPaginationReached = true)

                //If APPEND, load the next page or signal the end of pagination.
                LoadType.APPEND -> getRemoteKeyForLastItem(state)?.nextPage
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
            }

            //Fetch news from the API:
            val response = newsApi.getBreakingNews(
                country = country ?: "",
                page = currentPage,
                pageSize = state.config.pageSize
            ).also {
                try {
                    newsApi.getBreakingNews(
                        country = country ?: "",
                        page = currentPage,
                        pageSize = state.config.pageSize,
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
                if (loadType == LoadType.REFRESH) {
                    articleDao.clearAll()
                    breakingNewsRemoteKeysDao.deleteAllRemoteKeys()
                }

                //// Insert fetched articles to the database
                val articles = response.toArticle()
                Log.d("BreakingNewsRemoteMediator", "BreakingNews articles: ${articles.size}")
                val prevKey = if (currentPage == 1) null else currentPage - 1
                val nextKey = if (endOfPaginationReached) null else currentPage + 1

                // Insert remote keys to the database
                val keys = response.articles.map {
                    BreakingNewsRemoteKeyEntity(url = it.url, prevPage = prevKey, nextPage = nextKey)
                }
                // Insert articles and remote keys to the database
                breakingNewsRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                Log.d("BreakingNewsRemoteMediator", "BreakingNews keys: ${keys.size}")
                // Insert articles to the database
                articleDao.insertAll(articles)
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


    // These functions fetch the corresponding ArticleRemoteKeyEntity
    // based on the scroll position and type of load.
    private fun getRemoteKey(
        state: PagingState<Int, Article>,
        loadType: LoadType
    ): BreakingNewsRemoteKeyEntity? {

        return when (loadType) {
            LoadType.REFRESH -> {
                // state.anchorPosition: This is the position of the item
                // that is currently being displayed on the screen.
                state.anchorPosition?.let { position ->
                    // state.closestItemToPosition(position): Finds the item closest
                    // to the anchor position in the list.
                    state.closestItemToPosition(position)?.url?.let { url ->
                        //.url: Extracts the unique URL of the closest article
                        // (the URL is used as the key to identify each article).

                        // articleRemoteKeysDao.getRemoteKeys(url = url):
                        // Fetches the remote keys (previous and next page)
                        // for the article with the given url from the database.
                        breakingNewsRemoteKeysDao.getRemoteKeys(url = url)
                    }
                }
            }

            LoadType.PREPEND -> {
                state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.url?.let { url ->
                    breakingNewsRemoteKeysDao.getRemoteKeys(url = url)
                }
            }

            LoadType.APPEND -> {
                state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.url?.let { url ->
                    breakingNewsRemoteKeysDao.getRemoteKeys(url = url)
                }
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Article>): BreakingNewsRemoteKeyEntity? =
        getRemoteKey(state, LoadType.REFRESH)

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Article>): BreakingNewsRemoteKeyEntity? =
        getRemoteKey(state, LoadType.PREPEND)

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Article>): BreakingNewsRemoteKeyEntity? =
        getRemoteKey(state, LoadType.APPEND)
}


