package com.soe.dailynews.presentation.screens.explore

import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.util.CATEGORY_LIST

data class ExploreState (

    val selectedArticle: Article? = null,
    val selectedCategory: String = CATEGORY_LIST.first(),

    val searchQuery: String = "",

    val isLoading : Boolean = false,
    val error: String? = null,

)