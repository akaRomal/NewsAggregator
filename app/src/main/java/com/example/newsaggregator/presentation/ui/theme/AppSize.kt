package com.example.newsaggregator.presentation.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppSize(
    val micro: Dp,
    val small: Dp,
    val medium: Dp,
    val mediumLarge: Dp,
    val large: Dp,
    val widthBorder: Dp,
    val shadowElevation: Dp,
    val imageHeight: Dp,
    val imageWidth: Dp,
    val fullWidth: Float,
    val maxLinesTitleNews: Int,
    val maxLinesDescriptionNews: Int,
    val maxLinesAuthorNews: Int,
    val smallIconSize: Dp,
)

val size = AppSize(
    micro = 4.dp,
    small = 8.dp,
    medium = 16.dp,
    mediumLarge = 24.dp,
    large = 32.dp,
    widthBorder = 1.dp,
    shadowElevation = 8.dp,
    imageHeight = 100.dp,
    imageWidth = 140.dp,
    fullWidth = 1f,
    maxLinesTitleNews = 4,
    maxLinesDescriptionNews = 2,
    maxLinesAuthorNews = 1,
    smallIconSize = 32.dp,
)

val LocalAppSize = compositionLocalOf {
    size
}