package com.example.newsaggregator.presentation.screens.articles.model

data class NewsArticleItem(
    val articleId: Int? = null,
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val articleUrl: String = "",
    val tag: String = "",
    val author: String = "",
    val date: String = "",
)
