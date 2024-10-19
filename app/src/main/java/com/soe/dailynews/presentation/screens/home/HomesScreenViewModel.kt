package com.soe.dailynews.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.domain.usecase.ArticlesUseCase
import com.soe.dailynews.util.SOURCES
import com.soe.dailynews.util.filterArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class HomesScreenViewModel @Inject constructor(
    private val repository: ArticlesUseCase
) : ViewModel() {


    val getBreakingNews  = channelFlow {
        repository.getBreakingNews("us")
            .cachedIn(viewModelScope)
            .collectLatest {
                val validArticle = it.filterArticle()
                val cacheData = validArticle.map { article ->
                    Article(
                        source = article.source,
                        author = article.author,
                        url = article.url,
                        title = article.title,
                        description = article.description,
                        urlToImage = article.urlToImage,
                        publishedAt = article.publishedAt,
                        content = article.content
                    )
                }
                send(cacheData)
            }
    }
//
//    val getEverythingNews = channelFlow {
//        repository.getEverythingNews(SOURCES, "us")
//            .cachedIn(viewModelScope)
//            .collectLatest {
//                val validArticle = it.filterArticle()
//                val cacheData = validArticle.map { article ->
//                    Article(
//                        source = article.source,
//                        author = article.author,
//                        url = article.url,
//                        title = article.title,
//                        description = article.description,
//                        urlToImage = article.urlToImage,
//                        publishedAt = article.publishedAt,
//                        content = article.content
//                    )
//                }
//                send(cacheData)
//
//            }
//    }



//    val getBreakingNews: Flow<PagingData<Article>> = channelFlow {
//        repository.getBreakingNews("us")
//            .cachedIn(viewModelScope)
//            .collectLatest { articles ->
//
//                val validArticles = articles.filterArticle()
//
//                val cacheData = validArticles.map {
//                    Article(
//                        source = it.source,
//                        author = it.author,
//                        url = it.url,
//                        title = it.title,
//                        description = it.description,
//                        urlToImage = it.urlToImage,
//                        publishedAt = it.publishedAt,
//                        content = it.content
//                    )
//                }
//
//                send(cacheData)
//            }
//    }
//
//    val getEverythingNews: Flow<PagingData<Article>> = channelFlow {
//        repository.getEverythingNews(SOURCES)
//            .cachedIn(viewModelScope)
//            .collectLatest { articles ->
//                val validArticles = articles.filterArticle()
//
//                val cacheData = validArticles.map {
//                    Article(
//                        source = it.source,
//                        author = it.author,
//                        url = it.url,
//                        title = it.title,
//                        description = it.description,
//                        urlToImage = it.urlToImage,
//                        publishedAt = it.publishedAt,
//                        content = it.content
//                    )
//                }
//
//                send(cacheData)
//            }
//    }

}