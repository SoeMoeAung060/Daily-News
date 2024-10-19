package com.soe.dailynews.presentation.commom

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.soe.dailynews.R
import com.soe.dailynews.presentation.ui.theme.Dimensions.cornerNormal
import com.soe.dailynews.presentation.ui.theme.Dimensions.homeCardNewsHeight
import com.soe.dailynews.presentation.ui.theme.Dimensions.homeCardNewsHeightSmall
import com.soe.dailynews.presentation.ui.theme.Dimensions.homeCardNewsWidth
import com.soe.dailynews.presentation.ui.theme.Dimensions.homeCardNewsWidthSmall

@Composable
fun ArticleAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String? = null,
    context: Context
) {
    var imageLoaded by remember { mutableStateOf(false) }

    AsyncImage(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .border(
                if (!imageLoaded) 2.dp else 0.dp,
                MaterialTheme.colorScheme.surface,
                MaterialTheme.shapes.medium
            ),
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .placeholder(R.drawable.daily_news_logo)
            .error(R.drawable.daily_news_logo)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.FillBounds,
        onState = {
            imageLoaded = it is AsyncImagePainter.State.Success
        }
    )
}


@Composable
fun SquareAsyncCard(
    imageUrl: String,
    context: Context
) {
    ArticleAsyncImage(
        modifier = Modifier
            .size(homeCardNewsWidthSmall, homeCardNewsHeightSmall)
            .clip(RoundedCornerShape(cornerNormal))
            .background(MaterialTheme.colorScheme.background),
        imageUrl = imageUrl,
        context = context,
    )

}

@Composable
fun RectangleAsyncCard(
    imageUrl: String,
    context: Context
) {
    ArticleAsyncImage(
        modifier = Modifier
            .width(homeCardNewsWidth)
            .height(homeCardNewsHeight)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.background),
        imageUrl = imageUrl,
        context = context,
    )
}
