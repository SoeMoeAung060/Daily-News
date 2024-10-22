package com.soe.dailynews.presentation.screens.detail_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soe.dailynews.data.local.entity.BookmarkEntity
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.domain.usecase.ArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val articlesUseCase: ArticlesUseCase

): ViewModel() {



    var sideEffect by mutableStateOf<String?>(null)

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    Log.d("DetailScreenViewModel", "Before onEvent URL: ${event.article.url}")
                    val article = articlesUseCase.getBookmarkByUrl(event.article.url)
                    Log.d("DetailScreenViewModel", "GetBookmarkArticles: $article")
                    if (article == null){
                        upsertBookmarkArticles(event.article)
                    }else{
                        deleteBookmarkArticles(event.article)
                    }

                }
            }
            is DetailEvent.RemoveSideEffect -> {
                sideEffect
            }
        }
    }


    private suspend fun upsertBookmarkArticles(article: BookmarkEntity) {
//        Log.d("UpsertArticle", "upsertArticle: $article")
        articlesUseCase.upsertBookmarkArticle(article)
        sideEffect = "Article Saved"
    }

    private suspend fun deleteBookmarkArticles(article: BookmarkEntity) {
        articlesUseCase.deleteBookmarkArticle(article)
        sideEffect = "Article Deleted"
    }

    private suspend fun getAllBookmarkArticles(){
        articlesUseCase.getAllBookmarkArticles().collect{articles ->
            Log.d("DetailScreenViewModel", "Current articles in DB: $articles")
        }
    }


}