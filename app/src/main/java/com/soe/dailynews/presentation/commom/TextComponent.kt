package com.soe.dailynews.presentation.commom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.soe.dailynews.R


@Composable
fun ScreenTitleTextSmall(
    modifier: Modifier = Modifier,
    textResId: Int
) {
    Text(
        modifier = modifier,
        text = stringResource(id = textResId),
        color = MaterialTheme.colorScheme.tertiary,
        style = MaterialTheme.typography.headlineMedium,
    )
}


@Composable
fun CardTitleTextSmall(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text,
        maxLines = 3,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = MaterialTheme.typography.titleMedium.fontSize,
        overflow = TextOverflow.Ellipsis,
        fontWeight = MaterialTheme.typography.titleMedium.fontWeight
    )
}


@Composable
fun DescriptionTextSmall(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = MaterialTheme.typography.bodySmall.fontSize,
        style = MaterialTheme.typography.bodySmall,
        overflow = TextOverflow.Ellipsis,
    )
}


@Composable
fun CardLabelTextSmall(
    modifier: Modifier = Modifier,
    text: String,
    color : Color
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = 1,
        color = color,
        fontSize = MaterialTheme.typography.labelSmall.fontSize,
    )
}