package com.example.newsaggregator.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.newsaggregator.presentation.extensions.shimmerEffect
import com.example.newsaggregator.presentation.ui.theme.AppTheme

@Composable
fun Image(
    imageUrl: String,
    description: String?,
) {
    Box(
        modifier = Modifier
            .height(AppTheme.size.imageHeight)
            .width(AppTheme.size.imageWidth)
            .clip(AppTheme.shapes.image)
            .background(AppTheme.colorsScheme.surfacePrimary)
    ) {
        val imagePainter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
        )
        val painterState by imagePainter.state.collectAsState()
        when (painterState) {
            is AsyncImagePainter.State.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect()
                )
            }

            is AsyncImagePainter.State.Error -> {
                Image(
                    imageVector= Icons.Outlined.Close ,
                    contentDescription = description,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            else -> {
                Image(
                    painter = imagePainter,
                    contentDescription = description,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}