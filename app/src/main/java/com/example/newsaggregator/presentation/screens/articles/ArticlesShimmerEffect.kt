package com.example.newsaggregator.presentation.screens.articles

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.newsaggregator.presentation.extensions.shimmerEffect
import com.example.newsaggregator.presentation.ui.theme.AppTheme

@Composable
fun ArticlesShimmerEffect(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    contentAfterLoading: @Composable () -> Unit
) {
    if (isLoading) {
        Column(modifier = modifier.fillMaxSize().padding(horizontal = AppTheme.size.micro)) {
            Row(
                modifier = Modifier.padding(top = AppTheme.size.small),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.size.small)
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .height(AppTheme.size.mediumLarge)
                            .weight(AppTheme.size.fullWidth)
                            .clip(AppTheme.shapes.image)
                            .shimmerEffect()
                    )
                }
            }
            Spacer(
                modifier = Modifier.height(AppTheme.size.small)
            )
            repeat(5) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = AppTheme.colorsScheme.surfacePrimary,
                            shape = AppTheme.shapes.card
                        )
                        .border(
                            width = AppTheme.size.widthBorder,
                            color = AppTheme.colorsScheme.outline,
                            shape = AppTheme.shapes.card
                        )
                ) {
                    Row(
                        modifier = Modifier.padding(AppTheme.size.small),
                        horizontalArrangement = Arrangement.spacedBy(AppTheme.size.small)
                    ) {
                        Column(
                            modifier = Modifier.weight(AppTheme.size.fullWidth),
                            verticalArrangement = Arrangement.spacedBy(AppTheme.size.small)
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(AppTheme.size.medium)
                                    .width(AppTheme.size.large)
                                    .clip(AppTheme.shapes.image)
                                    .shimmerEffect()
                            )
                            repeat(3) {
                                Box(
                                    modifier = Modifier
                                        .height(AppTheme.size.medium)
                                        .fillMaxWidth()
                                        .clip(AppTheme.shapes.image)
                                        .shimmerEffect()
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .height(AppTheme.size.imageHeight)
                                .width(AppTheme.size.imageWidth)
                                .clip(AppTheme.shapes.image)
                                .shimmerEffect()
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(AppTheme.size.small)
                            .height(AppTheme.size.medium)
                            .fillMaxWidth()
                            .clip(AppTheme.shapes.image)
                            .shimmerEffect()
                    )
                }
            }
        }
    } else {
        contentAfterLoading()
    }
}