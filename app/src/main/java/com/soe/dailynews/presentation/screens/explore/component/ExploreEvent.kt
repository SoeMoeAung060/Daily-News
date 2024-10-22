package com.soe.dailynews.presentation.screens.explore.component

sealed class ExploreEvent {

    data class OnCategorySelected(val category: String) : ExploreEvent()

    data class  OnSearchQueryChange(val searchQuery: String) : ExploreEvent()

    data object OnSearch : ExploreEvent()


}