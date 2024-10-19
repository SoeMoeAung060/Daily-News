package com.soe.dailynews.presentation.screens.detail_screen

import android.view.ViewGroup
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyScopeMarker
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.kwabenaberko.newsapilib.models.Article
import com.soe.dailynews.R
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme

@Composable
fun DetailScreen(
//    viewModel: DetailScreenViewModel = hiltViewModel()

) {


//    val detailScreenState = viewModel.articleDetailState.collectAsStateWithLifecycle().value

    LazyColumn {
//        detailContent()
    }
}


@Composable
fun NavigateToWeb(url: String){


    // Decode the URL to its original form
//    val decodedUrl = Uri.decode(url)


    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            this.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }


    DisposableEffect(Unit) {
        // Destroy the WebView when the composable is disposed to avoid memory leaks
        onDispose {
            webView.destroy()
        }
    }

    AndroidView(factory = { webView }, update = {
        it.loadUrl(url)
    })
}

// UI

@LazyScopeMarker
fun LazyListScope.detailContent(
    modifier: Modifier = Modifier,
    urlToImage: String,
    title: String,
    author: String,
    publishedAt: String

){
    item {
        DetailImageAndHeader(
            urlToImage = urlToImage,
            title = title,
            author = author,
            publishedAt = publishedAt
        )
    }

    item {
        Spacer(modifier = Modifier.height(10.dp))
    }
    item {
        DetailContent()
    }
}



@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
) {




    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
            text = "description",
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun DetailImageAndHeader(
    modifier: Modifier = Modifier,
    urlToImage: String,
    title: String,
    author: String,
    publishedAt: String

) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(end = 8.dp)
                .clip(shape = MaterialTheme.shapes.large),
            model = ImageRequest.Builder(LocalContext.current)
                .data(urlToImage)
                .crossfade(true)
                .build(),
//            placeholder = painterResource(R.drawable.daily_news_logo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            color = MaterialTheme.colorScheme.primary,
            text = title,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = modifier.padding(end = 8.dp),
            ){
                Icon(
                    modifier = Modifier.padding(end = 5.dp),
                    painter = painterResource(R.drawable.editor),
                    contentDescription = "editor",
                )
                Text(
                    text = author,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                )
            }

            Text(
                text = publishedAt,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    DailyNewsTheme {
//        DetailScreen("https://www.google.com/")
    }
}