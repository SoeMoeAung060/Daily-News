package com.soe.dailynews.presentation.screens.home

import androidx.paging.PagingData
import com.soe.dailynews.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class HomeScreenState(

    val isLoading : Boolean = false,
    val article : Flow<PagingData<Article>>? = null,
    val breakingNews : List<Article> = emptyList(),
    val newsEverything : List<Article> = emptyList(),
    val error : String? = null,

    val searchQuery : String,
    val isSearchBarVisible : Boolean = false,

    )