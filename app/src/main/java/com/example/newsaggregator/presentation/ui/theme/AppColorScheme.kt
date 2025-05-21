package com.example.newsaggregator.presentation.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

// Палитра для светлой темы
val White = Color(0xFFFDFDFD) // фон
val Black = Color(0xFF010101) // текст
val Black05 = Color(0xFFF2F2F2) // фон Card
val Black15 = Color(0xFFD9D9D9) // фон тэгов
val Black30 = Color(0xFFB3B3B3) // бордер Card
val Gray30 = Color(0xFFB8B5B5) // shimmer base
val Gray75 = Color(0xFF8F8B8B) // shimmer highlight

// Палитра для тёмной темы (инвертированные оттенки)
val Black90 = Color(0xFF020202) // фон
val White100 = Color(0xFFEFEFEF) // текст
val Gray90 = Color(0xFF0D0D0D) // фон Card
val Gray80 = Color(0xFF262626) // фон тэгов
val Gray60 = Color(0xFF4C4C4C) // бордер Card
val Gray50 = Color(0xFF474A4A) // shimmer base
val Gray40 = Color(0xFF707474) // shimmer highlight

/**
 * Набор основных цветов приложения.
 *
 * @property primary Основной акцентный цвет.
 * @property onPrimary Цвет содержимого на фоне primary.
 * @property background Фоновый цвет экрана.
 * @property onBackground Цвет содержимого на фоне background.
 * @property surfacePrimary Фоновый цвет поверхностей (Card, BottomSheet и т.д.).
 * @property onSurfacePrimary Цвет содержимого на фоне surface.
 * @property onSurfaceSecondary Цвет содержимого на фоне surface.
 * @property surfaceVariant Альтернативный фон поверхностей (фон тэгов).
 * @property onSurfaceVariant Цвет содержимого на фоне surfaceVariant.
 * @property outline Цвет обводок и границ.
 * @property shimmerBase Базовый цвет эффекта shimmer.
 * @property shimmerHighlight Цвет подсветки эффекта shimmer.
 */
data class AppColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val background: Color,
    val onBackground: Color,
    val surfacePrimary: Color,
    val onSurfacePrimary: Color,
    val onSurfaceSecondary: Color,
    val surfaceTertiary: Color,
    val onSurfaceTertiary: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val icon: Color,
    val shimmerBase: Color,
    val shimmerHighlight: Color,
)

val LightColorScheme = AppColorScheme(
    primary = Black05,
    onPrimary = Black,
    background = White,
    onBackground = Black,
    surfacePrimary = Black05,
    onSurfacePrimary = Black,
    onSurfaceSecondary = Black30,
    surfaceTertiary = Black30,
    onSurfaceTertiary = Black,
    surfaceVariant = Black15,
    onSurfaceVariant = Black,
    outline = Black30,
    icon = Black05.copy(alpha = 0.9f),
    shimmerBase = Gray30,
    shimmerHighlight = Gray75,
)

val DarkColorScheme = AppColorScheme(
    primary = Gray90,
    onPrimary = White100,
    background = Black90,
    onBackground = White100,
    surfacePrimary = Gray90,
    onSurfacePrimary = White100,
    onSurfaceSecondary = White100,
    surfaceTertiary = White100,
    onSurfaceTertiary = Black90,
    surfaceVariant = Gray80,
    onSurfaceVariant = White100,
    outline = Gray60,
    icon = Gray90.copy(alpha = 0.9f),
    shimmerBase = Gray50,
    shimmerHighlight = Gray40,
)

val LocalAppColorScheme = compositionLocalOf {
    LightColorScheme
}