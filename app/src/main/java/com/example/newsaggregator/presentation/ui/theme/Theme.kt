package com.example.newsaggregator.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.newsaggregator.presentation.ui.theme.AppTheme.typography


@Composable
fun NewsAggregatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(
        LocalAppTypography provides typography,
        LocalAppShapes provides shapes,
        LocalAppColorScheme provides colorScheme,
        LocalAppSize provides size,
        content = content
    )
}

object AppTheme {
    val typography: AppTypography
        @Composable get() = LocalAppTypography.current

    val shapes: AppShapes
        @Composable get() = LocalAppShapes.current

    val colorsScheme: AppColorScheme
        @Composable get() = LocalAppColorScheme.current

    val size: AppSize
        @Composable get() = LocalAppSize.current
}