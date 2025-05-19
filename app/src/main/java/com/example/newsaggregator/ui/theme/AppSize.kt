package com.example.newsaggregator.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppSize(
    val small: Dp,
)

val size = AppSize(
    small = 16.dp
)

val LocalAppSize = compositionLocalOf {
    size
}
