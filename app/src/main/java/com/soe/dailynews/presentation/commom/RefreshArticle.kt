package com.soe.dailynews.presentation.commom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RefreshArticle(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit) {

    val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)

    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ){
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(alignment = Alignment.TopCenter)
        )
    }

}


@Preview(showBackground = true)
@Composable
private fun RefreshArticlePreview() {
    DailyNewsTheme {
        RefreshArticle(
            isRefreshing = true,
            onRefresh = {},
            content = {}
        )
    }

}