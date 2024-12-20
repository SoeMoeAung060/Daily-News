package com.soe.dailynews.presentation.commom

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.presentation.ui.theme.Dimensions.homeBreakingNewsHeight
import com.soe.dailynews.presentation.ui.theme.Dimensions.homeBreakingNewsWidth
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingExtraSmall
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingMedium


fun Modifier.breakingNewsEffect() = composed {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse,
        ), label = ""
    ).value
    background(color = MaterialTheme.colorScheme.onSurface.copy(alpha = alpha))
}


@Composable
fun BreakingNewsCardEffect(
    modifier: Modifier
){
    Column(
        modifier = modifier
            .width(homeBreakingNewsWidth)
            .height(homeBreakingNewsHeight),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .size(homeBreakingNewsWidth)
                .clip(MaterialTheme.shapes.medium)
                .breakingNewsEffect()
        )
        Spacer(modifier = Modifier.height(paddingExtraSmall))
        Box(
            modifier = Modifier
                .height(paddingMedium)
                .fillMaxWidth(0.9f)
                .breakingNewsEffect()
        )
        Spacer(modifier = Modifier.height(paddingExtraSmall))
        Box(
            modifier = Modifier
                .height(paddingMedium)
                .fillMaxWidth(0.9f)
                .breakingNewsEffect()
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BreakingNewsCardEffectPreview() {
    DailyNewsTheme {
        BreakingNewsCardEffect(modifier = Modifier)
    }
}