package com.soe.dailynews.presentation.commom

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soe.dailynews.R
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Icon(
                modifier = modifier.size(170.dp),
                painter = painterResource(id = R.drawable.daily_news_logo_horizontal),
                contentDescription = "logo",
                tint = Color.Unspecified
            )
        },
        actions = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.search),
                    contentDescription = "notification"
                )
            }

            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.notification),
                    contentDescription = "notification"
                )
            }
        },

        )

}




@Preview(showBackground = true)
@Composable
private fun TopBarPreview() {
    DailyNewsTheme {
        TopBar()
    }

}