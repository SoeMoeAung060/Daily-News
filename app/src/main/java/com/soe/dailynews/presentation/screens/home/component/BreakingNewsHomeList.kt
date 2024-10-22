package com.soe.dailynews.presentation.screens.home.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.soe.dailynews.R
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingLarge
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingMedium
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingSmall
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingZero

@Composable
fun BreakingNewsHomeList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {

//    when(val refreshState = articles.loadState.refresh){
//        is LoadState.Loading -> {
//            Box(
//                modifier = Modifier.fillMaxWidth(),
//                contentAlignment = Alignment.Center
//            ) {
////                CircularProgressIndicator()
//            }
//        }
//        is LoadState.Error -> {
//            // Handle and display error message
//            val errorMessage = refreshState.error.message ?: "Unknown error"
//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(text = "Something went wrong: $errorMessage")
//            }
//            Log.e("HomeScreen", "Error loading news: $errorMessage")
//        }
//
//        else -> {
            if(articles.itemCount > 0){
                LazyRow(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(paddingSmall)
                ){
                    items(count = minOf(articles.itemCount, 5)){index ->
                        val article = articles[index]
                        BreakingNewsCard(
                            modifier = Modifier.padding(
                                start = if (index == 0) paddingMedium else paddingZero,
                                end = if (index == 4) paddingMedium else paddingZero
                            ),
                            articles = article!!,
                            onClick = { onClick(article) }
                        )
                    }
                }
            }else{
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = stringResource(R.string.no_news_available),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

            }
//        }

//    }


}



