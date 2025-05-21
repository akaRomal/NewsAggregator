package com.example.newsaggregator.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.newsaggregator.R
import com.example.newsaggregator.presentation.ui.theme.AppTheme

@Composable
fun BookmarkIcon(
    modifier: Modifier = Modifier,
    onClickBookmark: (() -> Unit)? = null,
    bookmarked: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val icon = if (bookmarked) R.drawable.ic_bookmark_filled else R.drawable.ic_bookmark

    Box(
        modifier = modifier
            .size(AppTheme.size.smallIconSize)
            .border(
                width = AppTheme.size.widthBorder,
                color = AppTheme.colorsScheme.outline,
                shape = CircleShape
            )
            .background(
                color = AppTheme.colorsScheme.icon,
                shape = CircleShape
            )
            .then(
                if (onClickBookmark != null) Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = rememberRipple(
                        bounded = true,
                        radius = AppTheme.size.smallIconSize / 2
                    ),
                    onClick = onClickBookmark
                ) else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = stringResource(R.string.image_add_to_bookmarks),
            tint = AppTheme.colorsScheme.onPrimary
        )
    }
}