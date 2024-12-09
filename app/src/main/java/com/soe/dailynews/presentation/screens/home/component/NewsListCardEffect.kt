package com.soe.dailynews.presentation.screens.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soe.dailynews.presentation.commom.shimmerEffect
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingNormal



@Composable
fun ShimmerListItemForList(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    contentAfterLoading: @Composable () -> Unit,
) {

    if (isLoading) {
        Row(
            modifier = modifier
                .padding(horizontal = paddingNormal)
                .fillMaxWidth()
                .height(155.dp),
        ) {
            Spacer(
                modifier = Modifier
                    .size(155.dp)
                    .padding(vertical = 8.dp)
                    .shimmerEffect()
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp)
                    .fillMaxHeight()
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(vertical = 4.dp)
                        .shimmerEffect()
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .padding(vertical = 4.dp)
                        .shimmerEffect()
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(30.dp)
                        .padding(vertical = 4.dp)
                        .shimmerEffect()
                )
            }

        }
    } else {
        contentAfterLoading()
    }

}


@Preview(showBackground = true)
@Composable
private fun ShimmerListItemPreview() {
    DailyNewsTheme {
        ShimmerListItemForList(
            isLoading = true,
            contentAfterLoading = {}
        )
    }
    
}