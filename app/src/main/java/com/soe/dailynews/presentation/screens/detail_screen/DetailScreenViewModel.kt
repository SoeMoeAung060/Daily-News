package com.soe.dailynews.presentation.screens.detail_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse
import com.soe.dailynews.di.authenticationModule.AccountService
import com.soe.dailynews.domain.repository.ArticleRepository
import com.soe.dailynews.util.API_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
//
//class DetailScreenViewModel @Inject constructor(
//    private val accountService: AccountService,
//    private val articleRepository: ArticleRepository
//) : com.soe.dailynews.presentation.screens.NewsAppViewModel(){


//
//    private val _articleDetailState = MutableStateFlow(DetailScreenState())
//    val articleDetailState = _articleDetailState.asStateFlow()
//
//
//
//    init {
//
//
//    }
//
//
//    fun getDetailNews() {
//
//        viewModelScope.launch {
//
//            _articleDetailState.update {
//                it.copy(
//                    isLoading = true
//                )
//            }
//
//
//            articleRepository.getArticle()
//                .onRight {
//                    success ->
//                    _articleDetailState.update {
//                        it.copy(
//                            article = success
//                        )
//                    }
//                }
//
//                .onLeft {
//                    error ->
//                    _articleDetailState.update {
//                        it.copy(
//                            error = error.error.message
//                        )
//                    }
//                }
//
//            _articleDetailState.update {
//                it.copy(
//                    isLoading = false
//                )
//            }
//
//
//        }
//    }
//}