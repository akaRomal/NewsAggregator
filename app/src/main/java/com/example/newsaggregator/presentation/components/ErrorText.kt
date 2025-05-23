package com.example.newsaggregator.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.newsaggregator.presentation.ui.theme.AppTheme

@Composable
fun ErrorText(@StringRes message: Int) {
    Box(
        modifier = Modifier
            .padding(top = AppTheme.size.micro)
            .fillMaxWidth()
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = message),
            style = AppTheme.typography.titleMedium,
            modifier = Modifier
                .clip(AppTheme.shapes.card)
                .background(AppTheme.colorsScheme.error)
                .padding(vertical =  AppTheme.size.micro)
                .padding(horizontal =  AppTheme.size.small),
        )
    }
}