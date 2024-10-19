package com.soe.dailynews.presentation.screens.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soe.dailynews.R
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.domain.model.Source
import com.soe.dailynews.presentation.commom.CardLabelTextSmall
import com.soe.dailynews.presentation.commom.CardTitleTextSmall
import com.soe.dailynews.presentation.commom.IconExtraSmall
import com.soe.dailynews.presentation.commom.SquareAsyncCard
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.presentation.ui.theme.Dimensions.cornerMedium
import com.soe.dailynews.presentation.ui.theme.Dimensions.heightSemiNormal
import com.soe.dailynews.util.formatDate

@Composable
fun WorldNewsCard(
    modifier: Modifier = Modifier,
    articles: Article,
    onClick: () -> Unit
) {

//    if (articles.title.isEmpty() || articles.author?.isEmpty() ?: ) {
//        return
//    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(cornerMedium))
            .clickable { onClick.invoke() },
        elevation = CardDefaults.cardElevation(2.dp),

        ) {
        Row(
            modifier = modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
                .height(155.dp)
                .padding(8.dp),
        ) {
            Box(
                modifier = modifier.size(140.dp)
            ) {
                SquareAsyncCard(
                    imageUrl = articles.urlToImage ?: "",
                    context = LocalContext.current
                )
            }
            Column(
                modifier = modifier.padding(start = 8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                CardTitleTextSmall(
                    text = articles.title
                )

                Spacer(modifier = Modifier.height(height = heightSemiNormal))

                Row(
                    modifier = modifier.padding(end = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconExtraSmall(
                        painter = painterResource(R.drawable.editor),
                        contentDescription = stringResource(R.string.editor),
                    )

                    CardLabelTextSmall(
                        text = articles.author ?: "No author",
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                Spacer(modifier = Modifier.height(height = heightSemiNormal))


                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    CardLabelTextSmall(

                        text = articles.source.name,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    CardLabelTextSmall(
                        text = formatDate(articles.publishedAt),
                        color = MaterialTheme.colorScheme.secondary
                    )

                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
private fun CardPreview() {
    DailyNewsTheme {
        WorldNewsCard(
            articles = Article(
                author = "David Yang",
                source = Source(id = "1", name = "Google News"),
                title = "In a groundbreaking development, researchers from the Hayward Research Group at CU Boulder have unveiled a novel photomechanica",
                description = "This is a description",
                publishedAt = "2024.9.30",
                url = "https://www.google.com",
                urlToImage = "https://www.google.com",
                content = "This is a content"
            ),
            onClick = {}
        )
    }
}

