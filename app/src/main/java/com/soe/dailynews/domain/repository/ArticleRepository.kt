package com.soe.dailynews.domain.repository

import androidx.paging.PagingData
import com.soe.dailynews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    suspend fun getBreakingNews(country :String) : Flow<PagingData<Article>>

    suspend fun getNewsEverything(source : List<String>) : Flow<PagingData<Article>>
}