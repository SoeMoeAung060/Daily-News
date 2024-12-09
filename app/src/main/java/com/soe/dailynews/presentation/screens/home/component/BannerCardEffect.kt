package com.soe.dailynews.presentation.screens.home.component


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soe.dailynews.presentation.commom.shimmerEffect
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingNormal


@Composable
fun ShimmerListItemForBanner(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
            .padding(horizontal = paddingNormal, vertical = 4.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
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


@Preview(showBackground = true)
@Composable
fun PreviewShimmerEffect() {
    ShimmerListItemForBanner(
    )
}