package com.soe.dailynews.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.soe.dailynews.data.repository.ArticlesRepositoryPaging
import com.soe.dailynews.util.SOURCES
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomesScreenViewModel @Inject constructor(
    private val repository: ArticlesRepositoryPaging
) : ViewModel() {


    val getBreakingNews = repository.getBreakingNews("us")
        .cachedIn(viewModelScope)

    val getEverythingNews = repository.getEverythingNews(SOURCES, "us")
        .cachedIn(viewModelScope)



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