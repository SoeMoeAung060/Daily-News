package com.soe.dailynews.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.soe.dailynews.data.local.database.NewsDatabase
import com.soe.dailynews.data.remote.api.NewsApi
import com.soe.dailynews.data.repository.pagingSource.NewsPagingSource
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.domain.repository.ArticleRepository
import com.soe.dailynews.util.PER_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ArticleRepositoryImpl @Inject constructor (
    private val newsApi: NewsApi,
    private val newsDatabase: NewsDatabase
): ArticleRepository {


    private val pagingSourceFactory = newsDatabase.articleDao()

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getNews(
        sources: List<String>,
    ): Flow<PagingData<Article>> {

        return Pager(
            config = PagingConfig(pageSize = PER_PAGE_SIZE),
            pagingSourceFactory = { pagingSourceFactory.getAllArticle() },
            remoteMediator = RemoteMediator(
                newsApi = newsApi,
                newsDatabase = newsDatabase,
                sources = sources.joinToString(",")
            )
        ).flow
    }

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getTopHeadline(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = PER_PAGE_SIZE),
            pagingSourceFactory = { pagingSourceFactory.getAllArticle() },
            remoteMediator = RemoteMediator(
                newsApi = newsApi,
                newsDatabase = newsDatabase,
                sources = sources.joinToString(",")
            )
        ).flow
    }

    override suspend fun getSearchNews(search: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = PER_PAGE_SIZE),
            pagingSourceFactory = { NewsPagingSource(
                newsApi = newsApi,
                query = search,
                sources = sources.joinToString(",")) }
        ).flow
    }

    override suspend fun getCategoriesNews(category: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = PER_PAGE_SIZE),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    query = category
                )
            }
        ).flow
    }

}