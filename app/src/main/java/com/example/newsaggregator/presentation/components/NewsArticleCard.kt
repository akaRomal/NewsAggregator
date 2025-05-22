package com.example.newsaggregator.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.newsaggregator.presentation.ui.theme.AppTheme

@Composable
fun NewsArticleCard(
    onClick: () -> Unit,
    titleArticle: String,
    descriptionArticle: String,
    date: String,
    author: String,
    tags: List<String>,
    modifier: Modifier = Modifier,
    onClickTag: (String) -> Unit = {},
    onClickBookmark: (() -> Unit)? = null,
    bookmarked: Boolean = false,
    imageUrl: String? = null,
    imageDescription: String? = null,
) {
    CustomCard(
        onClick = onClick,
        modifier = modifier,
        onClickBookmark = onClickBookmark,
        bookmarked = bookmarked,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.size.small),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.size.small)
        ) {
            Column(modifier = Modifier.weight(AppTheme.size.fullWidth)) {
                if (tags.isNotEmpty()) {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(AppTheme.size.micro)) {
                        items(tags) {
                            TagsContainer(
                                onClick = { onClickTag(it) },
                                text = it
                            )
                        }
                    }
                }
                Text(
                    text = titleArticle,
                    color = AppTheme.colorsScheme.onSurfacePrimary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = AppTheme.size.maxLinesTitleNews,
                    style = AppTheme.typography.titleMedium,
                )
            }
            imageUrl?.let {
                Image(
                    imageUrl = it,
                    description = imageDescription
                )
            }
        }
        Text(
            text = descriptionArticle,
            modifier = Modifier.padding(horizontal = AppTheme.size.small),
            color = AppTheme.colorsScheme.onSurfacePrimary,
            overflow = TextOverflow.Ellipsis,
            maxLines = AppTheme.size.maxLinesDescriptionNews,
            style = AppTheme.typography.bodyMedium,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.size.small),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = date,
                color = AppTheme.colorsScheme.onSurfaceSecondary,
                maxLines = AppTheme.size.maxLinesAuthorNews,
                style = AppTheme.typography.labelSmall,
            )
            Text(
                text = author,
                color = AppTheme.colorsScheme.onSurfaceSecondary,
                overflow = TextOverflow.Ellipsis,
                maxLines = AppTheme.size.maxLinesAuthorNews,
                style = AppTheme.typography.labelSmall,
            )
        }
    }
}

@Composable
private fun CustomCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onClickBookmark: (() -> Unit)? = null,
    bookmarked: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    Surface(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = AppTheme.shapes.card,
        color = AppTheme.colorsScheme.surfacePrimary,
        contentColor = AppTheme.colorsScheme.onSurfacePrimary,
        shadowElevation = AppTheme.size.shadowElevation,
        border = BorderStroke(
            width = AppTheme.size.widthBorder,
            color = AppTheme.colorsScheme.outline
        ),
        interactionSource = interactionSource,
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(content = content)
            if (onClickBookmark != null) {
                BookmarkIcon(
                    onClickBookmark = onClickBookmark,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(AppTheme.size.medium),
                    bookmarked = bookmarked,
                )
            }
        }
    }
}