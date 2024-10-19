package com.soe.dailynews.data.remote.remoteMediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.soe.dailynews.data.local.database.NewsDatabase
import com.soe.dailynews.data.local.entity.ArticleRemoteKeyEntity
import com.soe.dailynews.data.mapper.toArticle
import com.soe.dailynews.data.remote.api.NewsApi
import com.soe.dailynews.domain.model.Article
import kotlinx.coroutines.delay
import java.net.SocketTimeoutException


@OptIn(ExperimentalPagingApi::class)
class ArticleRemoteMediator(
    private val query: String? = null,
    private val sources: String? = null,
    private val newsApi: NewsApi,
    private val newsDatabase: NewsDatabase
) : RemoteMediator<Int, Article>() {

    private val articleDao = newsDatabase.articleDao()
    private val articleRemoteKeysDao = newsDatabase.articleRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKey?.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKey?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
                    nextPage
                }
            }

            // Fetch Breaking News
            val breakingNewsResponse = newsApi.getBreakingNews(
                country = query ?: "", // Make sure query is valid, possibly use default
                page = currentPage,
                pageSize = state.config.pageSize
            )
            Log.d("ArticleRemoteMediator", "BreakingNews API Response: $breakingNewsResponse")

            // Determine pagination status
            val endOfPaginationReachedBreakingNews = breakingNewsResponse.articles.isEmpty()
            val prePage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReachedBreakingNews) null else currentPage + 1

            // Database transaction for Breaking News
            newsDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    articleDao.clearAll()
                    articleRemoteKeysDao.deleteAllRemoteKeys()
                }

                val articles = breakingNewsResponse.toArticle()

                // Add Remote Keys for Breaking News
                val breakingKeys = breakingNewsResponse.articles.map { article ->
                    ArticleRemoteKeyEntity(
                        url = article.url,
                        nextPage = nextPage,
                        prevPage = prePage
                    )
                }
                Log.d("ArticleRemoteMediator", "Breaking News Remote Keys: $breakingKeys")
                articleRemoteKeysDao.addAllRemoteKeys(remoteKeys = breakingKeys)
                articleDao.insertAll(articles)
            }

            // Everything News API Call (if applicable)
            val everythingNewsResponse = if (sources.isNullOrEmpty()) {
                newsApi.getNewsEverything(
                    source = sources!!,
                    page = currentPage,
                    pageSize = state.config.pageSize
                )
            } else null

            // Check if the Everything News API response is empty
            val endOfPaginationReachedEverythingNews = everythingNewsResponse!!.articles.isEmpty()

            val prePageEverything = if (currentPage == 1) null else currentPage - 1
            val nextPageEverything = if (endOfPaginationReachedEverythingNews) null else currentPage + 1

            // Database transaction for Everything News
            newsDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    articleDao.clearAll() // Depending on your design, you may want to clear here as well
                    articleRemoteKeysDao.deleteAllRemoteKeys()
                }

                val articles = everythingNewsResponse.toArticle()

                // Add Remote Keys for Everything News
                val everythingKeys = everythingNewsResponse.articles.map { article ->
                    ArticleRemoteKeyEntity(
                        url = article.url,
                        nextPage = nextPageEverything,
                        prevPage = prePageEverything
                    )
                }
                Log.d("ArticleRemoteMediator", "Everything News Remote Keys: $everythingKeys")
                everythingKeys?.let { articleRemoteKeysDao.addAllRemoteKeys(remoteKeys = it) }

                articleDao.insertAll(articles)

            }

            // Return the result of the operation
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReachedBreakingNews && endOfPaginationReachedEverythingNews)

        } catch (e: SocketTimeoutException) {
            MediatorResult.Error(Throwable("Network Timeout. Please Try Again."))
        } catch (e: retrofit2.HttpException) {
            if (e.code() == 429) {
                val retryAfter = e.response()?.headers()?.get("Retry-After")?.toIntOrNull() ?: 60
                delay(retryAfter * 1000L)
            }
            MediatorResult.Error(e)
        } catch (e: Exception) {
            e.printStackTrace()
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Article>
    ): ArticleRemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.url?.let { url ->
                articleRemoteKeysDao.getRemoteKeys(url = url)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Article>
    ): ArticleRemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { articleEntity ->
                articleRemoteKeysDao.getRemoteKeys(url = articleEntity.url)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Article>
    ): ArticleRemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { articleEntity ->
                articleRemoteKeysDao.getRemoteKeys(url = articleEntity.url)
            }
    }
}
