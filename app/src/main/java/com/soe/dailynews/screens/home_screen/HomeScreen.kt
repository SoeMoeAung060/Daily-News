package com.soe.dailynews.screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.soe.dailynews.ui.theme.DailyNewsTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    openAndPopUp: (String, String) -> Unit,
    viewModel: HomesScreenViewModel = hiltViewModel()
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Home Screen")

        Button(
            onClick = { viewModel.onClickSignOut(openAndPopUp) }
        )
        {
            Text(text = "Sign Out")

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    DailyNewsTheme {
        HomeScreen(
            openAndPopUp = { _, _ -> }
        )

    }

}