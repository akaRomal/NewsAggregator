package com.example.newsaggregator.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.newsaggregator.R

/**
 * Семейство шрифтов Roboto с различными весами.
 *
 * Используется для обеспечения единообразного внешнего вида текста в приложении.
 */
val roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.W400),
    Font(R.font.roboto_medium, FontWeight.W500),
    Font(R.font.roboto_semibold, FontWeight.W600),
    Font(R.font.roboto_bold, FontWeight.W700)
)