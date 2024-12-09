package com.soe.dailynews.presentation.commom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingExtraSmall
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingMedium
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingSmall
import com.soe.dailynews.presentation.ui.theme.Dimensions.toggleButtonHeight
import com.soe.dailynews.presentation.ui.theme.Dimensions.toggleButtonWidth

@Composable
fun GenericToggle(
    modifier: Modifier = Modifier,
    isChecked : Boolean,
    onCheckedChange : (Boolean) -> Unit,
) {

    Box(
        modifier = modifier
            .width(toggleButtonWidth)
            .height(toggleButtonHeight)
            .clip(RoundedCornerShape(toggleButtonWidth))
            .background(if(isChecked) Color.Blue else Color.White)
            .clickable {onCheckedChange(!isChecked)}
            .background(Color.Gray, CircleShape)
            .padding(horizontal = paddingExtraSmall),
        contentAlignment = Alignment.CenterStart

    ){
        Text(
            modifier = Modifier.padding(
                start = if(isChecked) paddingSmall else 0.dp,
                end = if(isChecked) 0.dp else paddingSmall
            ).align(
                if(isChecked) Alignment.CenterStart
                else Alignment.CenterEnd
            ),
            text = if(isChecked) "ON" else "OFF",
            color = Color.White,

        )
        Box(
            modifier = Modifier
                .size(paddingMedium)
                .background(Color.White, CircleShape)
                .align(if (isChecked) Alignment.CenterEnd else Alignment.CenterStart)
        )
    }

}


@Preview
@Composable
private fun GenericTogglePreview() {

    DailyNewsTheme {
        GenericToggle(
            isChecked = false,
            onCheckedChange = {}
            )
    }

}