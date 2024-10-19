package com.soe.dailynews.presentation.screens.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.soe.dailynews.R
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import kotlinx.coroutines.delay


private const val SPLASH_TIMEOUT = 500L


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    openAndPopUp : (String, String) -> Unit,
    viewModel: SplashScreenViewModel = hiltViewModel(),
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(R.drawable.daily_news_logo),
            contentDescription = "splash_screen"
        )
    }



    LaunchedEffect(Unit) {
        delay(SPLASH_TIMEOUT)
        viewModel.onAppStart(openAndPopUp)
    }

}






@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    DailyNewsTheme {
//        SplashScreen(
//            openAndPopUp = { route, popUp -> },
//            viewModel = SplashScreenViewModel()
//        )
    }

}