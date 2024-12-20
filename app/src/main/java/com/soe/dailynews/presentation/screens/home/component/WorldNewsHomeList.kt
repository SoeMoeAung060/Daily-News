@file:Suppress("NAME_SHADOWING")

package com.soe.dailynews.presentation.screens.home.component

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.soe.dailynews.R
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.navigations.navigation.ScreenRoute
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingLarge


fun LazyListScope.worldNewsHomeList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {

    if (articles.itemCount > 0){
        items(count = articles.itemCount) { index ->
            val articles = articles[index]
            if (articles != null) {
                WorldNewsCard(
                    modifier = Modifier.padding(),
                    articles = articles,
                    onClick = {
                        Log.d("TAG", "worldNewsHomeList: ${articles.title}")
                        onClick(articles) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun WorldNewsHomeListPreview() {
    DailyNewsTheme {
    }

}

