package com.soe.dailynews.presentation.screens.bookmark

import com.soe.dailynews.data.local.entity.BookmarkEntity
import com.soe.dailynews.domain.model.Article

data class BookmarkState(

    val articles: List<Article> = emptyList()
)
