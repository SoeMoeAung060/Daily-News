package com.soe.dailynews.presentation.screens.detail_screen

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyScopeMarker
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.soe.dailynews.R
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.util.cleanContent
import com.soe.dailynews.util.formatDate

@SuppressLint("QueryPermissionsNeeded")
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    article: Article,
    popUp: () -> Unit,
) {

    val context = LocalContext.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            DetailTopBar(
                modifier = modifier,
                onBackClick = popUp,
                onNetworkClick = {
                    Intent(Intent.ACTION_VIEW).also {
                        Log.d("DetailScreen", "url: ${article.url}")
                        it.data = Uri.parse(article.url)
                        try {
                            startActivity(context, it, null)
                        } catch (e: ActivityNotFoundException) {
                            Log.e("DetailScreen", "No activity found to handle the URL: ${article.url}")
                            Toast.makeText(context, "No browser found to open the link", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Log.e("DetailScreen", "Error occurred while opening the URL: $e")
                        }
//                        if (it.resolveActivity(context.packageManager) != null) {
//                            context.startActivity(it)
//                        } else {
//                            Log.d("DetailScreen", "No activity found to handle intent: $it")
//                        }
                    }
                },
                onBookMarkClick = {},
                onShareClick = {
                    Intent(Intent.ACTION_SEND).also {
                        it.putExtra(Intent.EXTRA_TEXT, article.url)
                        it.type = "text/plain"
                        if (it.resolveActivity(context.packageManager) != null) {
                            context.startActivity(it)
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        if (article.content.isEmpty()){
            ArticleWebView(article = article)
        }else{
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)

            ) {
                LazyColumn {
                    detailContent(
                        article = article
                    )

                }
            }
        }

    }
}


@Composable
fun ArticleWebView(article: Article) {
    val context = LocalContext.current

    AndroidView(
        factory = {
            android.webkit.WebView(context).apply {
                webViewClient = android.webkit.WebViewClient()
                loadUrl(article.url)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@LazyScopeMarker
fun LazyListScope.detailContent(
    article: Article,
    modifier: Modifier = Modifier

) {
    item {
        DetailImageAndHeader(
            urlToImage = article.urlToImage ?: "",
            title = article.title,
            author = article.author ?: "",
            publishedAt = article.publishedAt
        )
    }

    item {
        Spacer(modifier = Modifier.height(10.dp))
    }
    item {
        DetailContent(article = article)
    }
}


@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    article: Article
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        val articleContent = cleanContent(article.content)
        val briefContent = buildAnnotatedString {
            append(articleContent)

            withStyle(
                style = MaterialTheme.typography.bodyLarge.toSpanStyle().copy(
                    fontWeight = FontWeight.SemiBold

                )){
                    append(stringResource(R.string.read_more))
                }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.onSurface,
            text = briefContent,
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
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
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
            fontWeight = MaterialTheme.typography.displayLarge.fontWeight,
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
            ) {
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
                text = formatDate(publishedAt),
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
//        DetailScreen()
    }
}