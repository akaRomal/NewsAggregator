package com.example.newsaggregator.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Основная типографика приложения с различными стилями текста.
 *
 * Используется для определения стилей текста в различных компонентах пользовательского интерфейса.
 *
 * @property headlineLarge экранный заголовок (AppBar)
 * @property titleMedium заголовок списка новостей
 * @property bodyMedium описание в карточке
 * @property headlineSmall заголовок детали статьи
 * @property labelSmall доп. метаданные (дата, автор)
 * @property labelMedium чипы с тэгами
 * @property bodyLarge текст внутри WebView (если нужен нативный Text)
 */
data class AppTypography(
    val headlineLarge: TextStyle,
    val titleMedium: TextStyle,
    val bodyMedium: TextStyle,
    val headlineSmall: TextStyle,
    val labelSmall: TextStyle,
    val labelMedium: TextStyle,
    val bodyLarge: TextStyle,
)

val typography = AppTypography(
    headlineLarge = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.W700,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    labelSmall = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
)

val LocalAppTypography = compositionLocalOf {
    typography
}