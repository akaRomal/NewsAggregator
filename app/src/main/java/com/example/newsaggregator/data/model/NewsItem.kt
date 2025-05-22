package com.example.newsaggregator.data.model

data class NewsItem(
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val articleUrl: String = "",
    val tags: List<String> = emptyList(),
    val author: String = "",
    val date: String = "",
)
