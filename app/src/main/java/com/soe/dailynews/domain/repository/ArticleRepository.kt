package com.soe.dailynews.domain.repository

import androidx.paging.PagingData
import com.soe.dailynews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {


    suspend fun getNews(sources : List<String>) : Flow<PagingData<Article>>
    suspend fun getTopHeadline(sources : List<String>) : Flow<PagingData<Article>>
    suspend fun getSearchNews(search : String, sources : List<String>) : Flow<PagingData<Article>>
    suspend fun getCategoriesNews(category : String) : Flow<PagingData<Article>>

}