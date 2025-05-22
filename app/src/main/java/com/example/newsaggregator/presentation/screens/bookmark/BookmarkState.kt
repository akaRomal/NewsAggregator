package com.example.newsaggregator.presentation.screens.bookmark

import com.example.newsaggregator.presentation.model.BookmarkItem

data class BookmarkState(
    val newsArticleItems: List<BookmarkItem> = emptyList(),
)