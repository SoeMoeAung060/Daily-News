package com.soe.dailynews.presentation.screens.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.domain.model.Source
import com.soe.dailynews.presentation.commom.CardTitleTextSmall
import com.soe.dailynews.presentation.commom.RectangleAsyncCard
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.presentation.ui.theme.Dimensions.homeCardNewsHeight
import com.soe.dailynews.presentation.ui.theme.Dimensions.homeCardNewsWidth

@Composable
fun BreakingNewsCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    articles: Article
) {

    val context = LocalContext.current

    Column(
        modifier = modifier
            .width(homeCardNewsWidth)
            .height(homeCardNewsHeight + 80.dp)
            .clickable { onClick.invoke() },
    ){
        RectangleAsyncCard(
            imageUrl = articles.urlToImage ?: "",
            context = context
        )

        Spacer(modifier = Modifier.height(10.dp))
        CardTitleTextSmall(
            modifier = Modifier.padding(),
            text = articles.title,
        )
    }

}


@Preview
@Composable
private fun BreakingNewsCardPreview() {
    DailyNewsTheme {
        BreakingNewsCard(
            onClick = {},
            articles = Article(
                author = "David Yang",
                title = "In a groundbreaking development, researchers from the Hayward Research Group at CU Boulder have unveiled a novel photomechanica",
                description = "This is a description",
                publishedAt = "2024.9.30",
                url = "https://www.google.com",
                urlToImage = "https://www.google.com",
                content = "This is a content",
                source = Source(
                    id = "1",
                    name = "Google News"
                )
            )
        )
    }
    
}














@Preview(showBackground = true)
@Composable
private fun LatestNewsCardPreview() {
    DailyNewsTheme {
//        LatestNewsCard()
    }
}

@Preview(showBackground = true)
@Composable
private fun LatestNewsTitlePreview() {
    DailyNewsTheme {
//        LatestNewsTitle()
    }
}