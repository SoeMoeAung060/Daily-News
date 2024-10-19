package com.soe.dailynews.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.soe.dailynews.R
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.presentation.commom.ScreenTitleTextSmall
import com.soe.dailynews.presentation.commom.TopBar
import com.soe.dailynews.presentation.screens.home.component.BreakingNewsHomeList
import com.soe.dailynews.presentation.screens.home.component.worldNewsHomeList
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingLarge
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingSemiNormal
import com.soe.dailynews.util.dummyArticle
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Article) -> Unit,
    homesScreenViewModel: HomesScreenViewModel = hiltViewModel()
) {

    val everythingNews = homesScreenViewModel.getEverythingNews.collectAsLazyPagingItems()
    val breakingNews = homesScreenViewModel.getBreakingNews.collectAsLazyPagingItems()


    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        topBar = { TopBar() }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = paddingValues.calculateTopPadding())
                .background(MaterialTheme.colorScheme.background)
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth(),
            ) {

                item {
                    Spacer(modifier = Modifier.height(paddingSemiNormal))

                    ScreenTitleTextSmall(
                        modifier = Modifier.padding(start = paddingLarge),
                        textResId = R.string.breaking_news
                    )

                    Spacer(modifier = Modifier.height(paddingSemiNormal))

                    BreakingNewsHomeList(
                        modifier = Modifier.fillMaxWidth(),
                        articles = breakingNews,
                        onClick = { article -> navigateToDetail(article) }
                    ).also {
                        println("Breaking News - Articles count: ${breakingNews.itemCount}")

                    }
                }


                item {
                    Spacer(modifier = Modifier.height(paddingSemiNormal))

                    ScreenTitleTextSmall(
                        modifier = Modifier.padding(start = paddingLarge),
                        textResId = R.string.world_wide
                    )

                    Spacer(modifier = Modifier.height(paddingSemiNormal))

                }

                worldNewsHomeList(
                    modifier = Modifier.fillMaxWidth(),
                    articles = everythingNews,
                    onClick = { article -> navigateToDetail(article) }
                ).also {
                    println("Everything News - Articles count: ${everythingNews.itemCount}")
                }
            }
        }
    }

}



@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    DailyNewsTheme {

        val articlesFlow = flowOf(PagingData.from(listOf(dummyArticle, dummyArticle, dummyArticle, dummyArticle)))
        val lazyPagingArticles = articlesFlow.collectAsLazyPagingItems()

        HomeScreen(
//            breakingNews = lazyPagingArticles,
//            everythingNews = lazyPagingArticles,
            navigateToDetail = {}
        )
    }

}