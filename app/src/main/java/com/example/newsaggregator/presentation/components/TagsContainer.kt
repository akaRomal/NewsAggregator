package com.example.newsaggregator.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.newsaggregator.presentation.ui.theme.AppTheme

@Composable
fun TagsContainer(
    onClick: () -> Unit,
    text: String,
    selected: Boolean = false,
) {
    val color =
        if (selected) AppTheme.colorsScheme.surfaceTertiary else AppTheme.colorsScheme.surfaceVariant
    Box(
        modifier = Modifier
            .clip(AppTheme.shapes.image)
            .background(color)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(AppTheme.size.micro),
            color = AppTheme.colorsScheme.onSurfaceVariant,
            maxLines = AppTheme.size.maxLinesAuthorNews,
            style = AppTheme.typography.labelSmall,
        )
    }
}