package com.soe.dailynews.presentation.screens.bookmark.component

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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soe.dailynews.R
import com.soe.dailynews.data.local.entity.BookmarkEntity
import com.soe.dailynews.data.mapper.toBookmarkArticle
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.presentation.commom.CardTitleTextSmall
import com.soe.dailynews.presentation.commom.DescriptionTextSmall
import com.soe.dailynews.presentation.commom.ExploreArticleImage
import com.soe.dailynews.presentation.commom.IconExtraSmall
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingLarge
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingNormal
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingSemiNormal
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingSmall
import com.soe.dailynews.util.dummyArticle
import com.soe.dailynews.util.formatDate


@Composable
fun BookmarkCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (() -> Unit)? = null
) {

    if (article.title.isEmpty() || article.url.isEmpty()){
        return
    }

    val context = LocalContext.current

    Column {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp)
                .clickable {onClick?.invoke()}
                .padding(horizontal = paddingNormal)
                .background(MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.Top,
        ) {
            Column(
                modifier = modifier.weight(1f)
                    .padding(paddingSmall),

                ){

                CardTitleTextSmall(
                    modifier = Modifier.weight(1f),
                    text = article.title
                )

                Spacer(Modifier.height(paddingSmall))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {
                    DescriptionTextSmall(
                        text = article.source.name,
                    )

                    Spacer(Modifier.width(paddingLarge))

                    IconExtraSmall(
                        painter = painterResource(id = R.drawable.time),
                        contentDescription = "time",
                    )

                    DescriptionTextSmall(
                        text = formatDate(article.publishedAt),
                    )

                }

            }

            Spacer(Modifier.width(paddingSmall))

            ExploreArticleImage(
                modifier = modifier.size(100.dp),
                imageUrl = article.urlToImage ?: "",
                context = context
            )
        }

        Spacer(Modifier.height(paddingSemiNormal))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = paddingNormal)
                .height(1.dp)
                .background(MaterialTheme.colorScheme.outline.copy(0.1f))
        )
    }








}


@Preview
@Composable
private fun ExploreCardScreenPreview() {
    DailyNewsTheme {
        BookmarkCard(
            article = dummyArticle,
            modifier = Modifier
        )

    }

}