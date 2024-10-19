package com.soe.dailynews.presentation.screens.detail_screen

import com.soe.dailynews.domain.model.Article

data class DetailScreenState(

    val isLoading : Boolean = false,
    val article : List<Article> = emptyList(),
    val error : String? = null

)
