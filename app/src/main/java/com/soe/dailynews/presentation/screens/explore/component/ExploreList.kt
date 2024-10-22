package com.soe.dailynews.presentation.screens.explore.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.presentation.commom.EmptyScreen
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingMedium

@Composable
fun ExploreList(
    modifier: Modifier = Modifier,
    articles : LazyPagingItems<Article>,
    onClick: (Article) -> Unit

) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
//        contentPadding = PaddingValues(4.dp)

    ){
      items(articles.itemCount){
          articles[it]?.let { article ->
              ExploreCard(
                  article = article,
                  onClick = {onClick(article)}
              )
          }
      }
    }

}
