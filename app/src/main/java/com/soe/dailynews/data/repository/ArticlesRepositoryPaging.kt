package com.soe.dailynews.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.soe.dailynews.data.local.database.NewsDatabase
import com.soe.dailynews.data.remote.api.NewsApi
import com.soe.dailynews.data.remote.remoteMediator.ArticleRemoteMediator
import com.soe.dailynews.domain.model.Article
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class ArticlesRepositoryPaging @Inject constructor(
    private val newApi : NewsApi, // API service to fetch ArticleDTOs
    private val newsDatabase: NewsDatabase // Local database to store and retrieve ArticleDTOs
) {


     @OptIn(ExperimentalPagingApi::class)
     fun getBreakingNews(country: String): Flow<PagingData<Article>> {
        val pagingSourceFactory = { newsDatabase.articleDao().getAllArticle() }
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = ArticleRemoteMediator(
                query = country,
                newsApi = newApi,
                newsDatabase = newsDatabase
            )
        ).flow

    }

    @OptIn(ExperimentalPagingApi::class)
    fun getEverythingNews (source: List<String>): Flow<PagingData<Article>> {
        val pagingSourceFactory = { newsDatabase.articleDao().getAllArticle() }
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = ArticleRemoteMediator(
                sources = source.joinToString(separator = ","),
                newsApi = newApi,
                newsDatabase = newsDatabase
            )
        ).flow
    }

}