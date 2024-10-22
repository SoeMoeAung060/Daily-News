package com.soe.dailynews.presentation.screens.explore

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.room.util.query
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.domain.usecase.ArticlesUseCase
import com.soe.dailynews.presentation.screens.explore.component.ExploreEvent
import com.soe.dailynews.presentation.screens.explore.component.ExploreEvent.OnCategorySelected
import com.soe.dailynews.presentation.screens.explore.component.ExploreEvent.OnSearch
import com.soe.dailynews.presentation.screens.explore.component.ExploreEvent.OnSearchQueryChange
import com.soe.dailynews.util.CATEGORY_LIST
import com.soe.dailynews.util.FIVE_MINUTES_MILLIS
import com.soe.dailynews.util.SOURCES
import com.soe.dailynews.util.filterArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val articlesUseCase: ArticlesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ExploreState())
    val state: State<ExploreState> = _state


    private val _searchResult = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val searchResult: StateFlow<PagingData<Article>> = _searchResult


    private val _isRefreshing = mutableStateOf(false)
    val isRefreshing: State<Boolean> = _isRefreshing

    private var _selectedCategory by mutableStateOf(CATEGORY_LIST.first())


    //Selected Category
    private val _articles = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val articles: StateFlow<PagingData<Article>> = _articles.asStateFlow()


    init {
        getInitialDataForAllCategory()
        startAutoRefresh()
    }

    fun onEvent(event: ExploreEvent) {
        when (event) {
            is OnSearchQueryChange -> {
                _state.value = state.value.copy(searchQuery = event.searchQuery)
            }

            is OnCategorySelected -> {
                _state.value = state.value.copy(selectedCategory = event.category)
                _selectedCategory = event.category
                getCategoriesNews(_selectedCategory)

            }

            is OnSearch -> {
                searchNews()
            }
        }
    }


    private fun startAutoRefresh() {
        viewModelScope.launch {
            while (isActive) {
                delay(FIVE_MINUTES_MILLIS)
                refreshAllCategory()
            }
        }
    }

    fun refreshArticle() {
        _isRefreshing.value = true
        viewModelScope.launch {
            refreshAllCategory()
            _isRefreshing.value = false
        }
    }


    private fun refreshAllCategory() {
        CATEGORY_LIST.forEach { category ->
            refreshCategoriesNews(category = category)
        }
    }


    private fun refreshCategoriesNews(category: String) {
        viewModelScope.launch {
            getCategoriesNews(category = category)
        }
    }


    private fun getInitialDataForAllCategory() {
        CATEGORY_LIST.forEach { category ->
            getCategoriesNews(category = category)
        }
    }

     private fun getCategoriesNews(category: String) {
        viewModelScope.launch {
            articlesUseCase.getCategoriesNews(
                category = category,
            ).cachedIn(viewModelScope)
                .collectLatest {

                    val validArticle = it.filterArticle()
                    Log.d("ExploreViewModel", "Fetched $validArticle articles for category: $category")


                    if(category == state.value.selectedCategory){
                        _articles.value = validArticle
                    }
                }
        }
    }


    private fun searchNews() {
        viewModelScope.launch {
            val query = state.value.searchQuery
            if (query.isNotBlank()) {
                articlesUseCase.searchNews(
                    query = state.value.searchQuery,
                    sources = SOURCES
                ).cachedIn(viewModelScope)
                    .collectLatest {
                        _searchResult.value = it.filterArticle()
                        println("Search result update : ${_searchResult.value} articles found.")
                    }
            }else{
                _searchResult.value = PagingData.empty()
            }

        }
    }
}