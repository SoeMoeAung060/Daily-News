package com.soe.dailynews.presentation.screens.detail_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.soe.dailynews.R
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    onBackClick: () -> Unit,
    onBookMarkClick: () -> Unit,
    onShareClick: () -> Unit,
    onNetworkClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.tertiary,
            titleContentColor = MaterialTheme.colorScheme.tertiary
        ),
        title = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "back"
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onBookMarkClick() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.bookmark),
                    contentDescription = "bookmark"
                )
            }
            IconButton(
                onClick = { onShareClick() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = "share"
                )
            }

            IconButton(
                onClick = { onNetworkClick() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.network),
                    contentDescription = "network"
                )
            }

        }
    )
}


@Preview
@Composable
private fun DetailTopBarPreview() {
    DailyNewsTheme {
        DetailTopBar(
            onBackClick = {},
            onBookMarkClick = {},
            onNetworkClick = {},
            onShareClick = {}
        )

    }
    
}