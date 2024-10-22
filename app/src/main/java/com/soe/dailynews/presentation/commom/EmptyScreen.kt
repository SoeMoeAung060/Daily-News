package com.soe.dailynews.presentation.commom

import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import com.soe.dailynews.R
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.presentation.ui.theme.Dimensions.iconErrorSize
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingNormal
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException


@Composable
fun EmptyScreen(
    error: LoadState.Error? = null
) {

    val context = LocalContext.current

    var icon by remember{
        mutableIntStateOf(R.drawable.ic_network_error)
    }

    var message by remember{
        mutableStateOf(
            parseErrorMessage(
            context = context,
            error = error)
        )
    }


    if (error == null){
        message = context.getString(R.string.no_save_news)
        icon = R.drawable.ic_search_document
    }

    if (error?.error is HttpException && (error.error as HttpException).code() == 429){
        message = context.getString(R.string.rate_limit_exceeded)
        icon = R.drawable.ic_network_error
    }

    val startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.3f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )


    EmptyContent(
        icon = icon,
        alphaAni = alphaAnimation,
        message = message
    )

}

fun parseErrorMessage(context: Context, error: LoadState.Error?): String {

    return when(error?.error){
        is SocketTimeoutException -> {
            context.getString(R.string.server_unavailable)
        }
        is ConnectException -> {
            context.getString(R.string.internet_unavailable)
        }
        is HttpException -> {
            context.getString(R.string.rate_limit_exceeded)
        }
        else -> {
            context.getString(R.string.unknown_error)
        }

    }

}


@Composable
fun EmptyContent(
    icon : Int,
    alphaAni : Float,
    message : String
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(iconErrorSize)
                .alpha(alphaAni),
            tint = if (isSystemInDarkTheme()) LightGray else DarkGray
        )

        Text(
            text = message,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Normal),
            color = if (isSystemInDarkTheme()) LightGray else DarkGray,
            modifier = Modifier
                .alpha(alphaAni)
                .padding(paddingNormal),
            textAlign = TextAlign.Center
            )
    }
}


@Preview
@Composable
private fun EmptyScreenPreview() {
    DailyNewsTheme {
        EmptyContent(
            icon = R.drawable.ic_network_error,
            alphaAni = 0.5f,
            message = "Something went wrong"
        )
    }

}