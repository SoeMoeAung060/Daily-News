package com.soe.dailynews.presentation.screens.detail_screen

import com.soe.dailynews.data.local.entity.BookmarkEntity
import com.soe.dailynews.domain.model.Article

sealed class DetailEvent{

    data object RemoveSideEffect : DetailEvent()

    data class UpsertDeleteArticle(val article: BookmarkEntity) : DetailEvent()
}
