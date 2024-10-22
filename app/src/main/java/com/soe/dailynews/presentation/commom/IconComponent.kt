package com.soe.dailynews.presentation.commom

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soe.dailynews.R
import com.soe.dailynews.presentation.ui.theme.Dimensions.iconMedium
import com.soe.dailynews.presentation.ui.theme.Dimensions.iconXXSmall
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingExtraSmall

@Composable
fun IconExtraSmall(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String,
) {
    Icon(
        modifier = modifier
            .padding(end = paddingExtraSmall)
            .size(iconMedium),
        painter = painter,
        contentDescription = contentDescription,
        tint = MaterialTheme.colorScheme.secondary
    )
}




@Composable
fun IconNormal(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String,
) {
    Icon(
        modifier= modifier.size(18.dp),
        painter = painter,
        contentDescription = contentDescription,
        tint = MaterialTheme.colorScheme.onSurface
    )

}


@Composable
fun IconButtonNormal(
    modifier: Modifier = Modifier,
    painter: Painter,
    onClick: () -> Unit,
    contentDescription: String,
) {

    IconButton(
        onClick = onClick,
    ) {
        Icon(
            modifier= modifier.size(20.dp),
            painter = painter,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }

}


