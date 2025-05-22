package com.example.newsaggregator.presentation.screens.articles

import com.example.newsaggregator.presentation.model.NewsArticleItem

data class ArticlesState(
    val isLoading: Boolean = true,
    val isNetworkError: Boolean = false,
    val isTimeoutError: Boolean = false,
    val isServerError: Boolean = false,
    val isUnknownError: Boolean = false,
    val tagCloudItems: List<String> = emptyList(),
    val selectedTag: String = "",
    val newsArticleItems: List<NewsArticleItem> = emptyList(),
)