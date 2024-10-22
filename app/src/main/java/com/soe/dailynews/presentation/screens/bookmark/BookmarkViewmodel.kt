package com.soe.dailynews.presentation.screens.bookmark

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soe.dailynews.domain.usecase.ArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class BookmarkViewmodel @Inject constructor(
    private val articlesUseCase: ArticlesUseCase

): ViewModel() {

    private var _state = mutableStateOf(BookmarkState())
    val state : State<BookmarkState> = _state

    init {
        getArticles()
    }

     private fun getArticles() {
        val getArticle = articlesUseCase.getAllBookmarkArticles()
         Log.d("GetArticle", "getArticles: $getArticle")
            getArticle.onEach { bookmarkArticles ->
                Log.d("GetArticle", "received Article: $bookmarkArticles")
            _state.value = _state.value.copy(
                articles = bookmarkArticles
            )
        }.launchIn(viewModelScope)
    }

}