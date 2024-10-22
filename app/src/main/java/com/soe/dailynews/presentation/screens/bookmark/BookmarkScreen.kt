package com.soe.dailynews.presentation.screens.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.soe.dailynews.R
import com.soe.dailynews.data.local.entity.BookmarkEntity
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.presentation.commom.ScreenTitleTextLarge
import com.soe.dailynews.presentation.screens.bookmark.component.BookmarkList
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.util.dummyArticle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetail: (Article) -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TopAppBar(
            title = {
                ScreenTitleTextLarge(
                    textResId = R.string.bookmark
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
        )

        BookmarkList(
            articles = state.articles,
//            articles = dummyArticle.toArticleList(),
            onClick = { navigateToDetail(it) }
        )
    }



}


@Preview
@Composable
private fun BookMarkScreenPreview() {
    DailyNewsTheme {
//        BookmarkScreen(
//            state = BookmarkState(
//                articles = dummyArticle.toArticleList()
//            ),
//            navigateToDetail = {},
//        )
    }

}