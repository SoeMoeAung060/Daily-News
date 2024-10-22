package com.soe.dailynews.presentation.screens.bookmark.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.soe.dailynews.data.local.entity.BookmarkEntity
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.presentation.commom.EmptyScreen
import com.soe.dailynews.presentation.screens.explore.component.ExploreCard
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingExtraSmall
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingSmall

@Composable
fun BookmarkList(
    modifier: Modifier = Modifier,
    articles : List<Article>,
    onClick: (Article) -> Unit
) {

    if (articles.isEmpty()){
        EmptyScreen()
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(paddingSmall),
        contentPadding = PaddingValues(all = paddingExtraSmall)
    ) {
        items(count = articles.size){
            val article = articles[it]
            BookmarkCard(
                article = article,
                onClick = { onClick(article) }
            )
        }
    }

}

