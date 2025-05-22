package com.example.newsaggregator.presentation.model

data class NewsArticleItem(
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val articleUrl: String = "",
    val tag: List<String> = emptyList(),
    val author: String = "",
    val date: String = "",
)