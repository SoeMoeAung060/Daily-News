package com.soe.dailynews.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.soe.dailynews.data.repository.ArticlesRepositoryPaging
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.util.SOURCES
import com.soe.dailynews.util.filterArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomesScreenViewModel @Inject constructor(
    private val repository: ArticlesRepositoryPaging
) : ViewModel() {


    val getBreakingNews = repository.getBreakingNews("us")
        .cachedIn(viewModelScope)
        .map { pagingData ->
            pagingData.filterArticle()
        }

    val getEverythingNews = repository.getEverythingNews(SOURCES)
        .cachedIn(viewModelScope)
        .map { pagingData ->
            pagingData.filterArticle()
        }


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