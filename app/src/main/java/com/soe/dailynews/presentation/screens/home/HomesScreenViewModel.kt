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


    val getEverythingNews  = channelFlow {
        repository.getNews(SOURCES)
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


    val getTopHeadline  = channelFlow {
        repository.getTopHeadline(SOURCES)
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

}