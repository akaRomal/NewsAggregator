package com.example.newsaggregator.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Представляет набор скруглений углов для компонентов пользовательского интерфейса.
 *
 * Используется для задания различных уровней скругления углов элементов, что позволяет создавать
 * визуально согласованный дизайн в приложении.
 *
 * @property card для Card
 * @property image картинок
 */
data class AppShapes(
    val card: Shape,
    val image: Shape,
)

val shapes = AppShapes(
    card = RoundedCornerShape(8.dp),
    image = RoundedCornerShape(12.dp),
)

val LocalAppShapes = compositionLocalOf {
    shapes
}