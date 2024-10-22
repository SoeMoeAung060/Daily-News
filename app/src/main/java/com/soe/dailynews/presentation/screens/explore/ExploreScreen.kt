package com.soe.dailynews.presentation.screens.explore

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.soe.dailynews.R
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.presentation.commom.ScreenTitleTextLarge
import com.soe.dailynews.presentation.commom.SearchBar
import com.soe.dailynews.presentation.screens.explore.component.ExploreCategory
import com.soe.dailynews.presentation.screens.explore.component.ExploreEvent
import com.soe.dailynews.presentation.screens.explore.component.ExploreList
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingMedium
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingSemiMedium
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingSmall
import com.soe.dailynews.util.CATEGORY_LIST
import com.soe.dailynews.util.dummyArticle
import kotlinx.coroutines.flow.flowOf



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    exploreState: ExploreState,
    modifier: Modifier = Modifier,
    event: (ExploreEvent) -> Unit,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    articles: LazyPagingItems<Article>,
    navigateToDetails: (Article) -> Unit,
    searchResults: LazyPagingItems<Article>
) {

    var isSearchActive by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
//        when (val refreshState = articles.loadState.refresh) {
//            is LoadState.Loading -> {
//                // Show loading indicator
//                CircularProgressIndicator()
//            }
//            is LoadState.Error -> {
//                // Log and show error message
//                Log.e("ExploreScreen", "Error loading articles", refreshState.error)
//                Text("Error loading articles: ${refreshState.error.message}")
//            }
//            is LoadState.NotLoading -> {
//                // Proceed to display articles
//                if (articles.itemCount == 0) {
//                    Text("No articles found")
//                } else {
//                    ExploreList(
//                        modifier = Modifier.padding(horizontal = paddingSmall),
//                        articles = articles,
//                        onClick = { article -> navigateToDetails(article) }
//                    )
//                }
//            }
//        }

        if (!isSearchActive) {
            TopAppBar(
                title = {
                    ScreenTitleTextLarge(textResId = R.string.explore)
                },
                actions = {
                    IconButton(onClick = { isSearchActive = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
//                modifier = Modifier.padding(paddingSemiMedium)

            )
        } else {
            Box(
                modifier = Modifier
                    .padding(
                        top = paddingMedium,
                        start = paddingSemiMedium,
                        end = paddingSemiMedium
                    )
                    .statusBarsPadding()
            ) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = exploreState.searchQuery,
                    readOnly = false,
                    onValueChange = { event(ExploreEvent.OnSearchQueryChange(it)) },
                    onSearch = { event(ExploreEvent.OnSearch) },
                    onSearchClosed = {
                        isSearchActive = false // Close search
                    }
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            if (!isSearchActive) {
                ExploreCategory(
                    categories = CATEGORY_LIST,
                    onCategorySelected = {
                        event(ExploreEvent.OnCategorySelected(it))
                    },
                    selectedCategory = exploreState.selectedCategory
                )

                Spacer(modifier = Modifier.height(paddingSmall))

                ExploreList(
                    modifier = Modifier.padding(),
                    articles = articles,
                    onClick = { article -> navigateToDetails(article) }
                )
//                RefreshArticle(
//                    isRefreshing = isRefreshing,
//                    onRefresh = onRefresh,
//                    modifier = Modifier
//                ) {
//                    ExploreList(
//                        modifier = Modifier.padding(horizontal = paddingSmall),
//                        articles = articles,
//                        onClick = { article -> navigateToDetails(article) }
//                    )
//                }

            } else {
                Spacer(modifier = Modifier.height(paddingMedium))

                if (searchResults.itemCount > 0) {
                    ExploreList(
                        modifier = Modifier.padding(horizontal = paddingSmall),
                        articles = searchResults,
                        onClick = { article -> navigateToDetails(article) }
                    )
                } else {
                    println("No search results found for query: ${exploreState.searchQuery}")
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun TopBarPreview() {

    val articlesFlow = flowOf(PagingData.from(listOf(dummyArticle, dummyArticle, dummyArticle)))
    val lazyPagingItems = articlesFlow.collectAsLazyPagingItems()
    DailyNewsTheme {
        ExploreScreen(
            exploreState = ExploreState(),
            event = {},
            isRefreshing = false,
            onRefresh = {},
            articles = lazyPagingItems,
            navigateToDetails = {},
            searchResults = lazyPagingItems
        )
    }

}


