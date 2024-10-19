package com.soe.dailynews.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.soe.dailynews.data.local.database.NewsDatabase
import com.soe.dailynews.data.remote.api.NewsApi
import com.soe.dailynews.data.repository.pagingSource.BreakingNewsPagingSource
import com.soe.dailynews.data.repository.remoteMediator.RemoteMediator
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.domain.repository.ArticleRepository
import com.soe.dailynews.util.PER_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ArticleRepositoryImpl @Inject constructor (
    private val newsApi: NewsApi,
    private val newsDatabase : NewsDatabase
): ArticleRepository {


    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getBreakingNews(country: String): Flow<PagingData<Article>> {
        val pagingSourceFactory = { newsDatabase.articleDao().getAllArticle() }
        return Pager(
            config = PagingConfig(pageSize = PER_PAGE_SIZE),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = RemoteMediator(
                country = country,
                newsApi = newsApi,
                newsDatabase = newsDatabase
            )
        ).flow
    }

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getEverythingNews(source: List<String>, country: String): Flow<PagingData<Article>> {
        val pagingSourceFactory = { newsDatabase.articleDao().getAllArticle() }
        return Pager(
            config = PagingConfig(pageSize = PER_PAGE_SIZE),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = RemoteMediator(
                country = country,
                sources = source.joinToString { "," },
                newsApi = newsApi,
                newsDatabase = newsDatabase
            )
        ).flow
    }
}