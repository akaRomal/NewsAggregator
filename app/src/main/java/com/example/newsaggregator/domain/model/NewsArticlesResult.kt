package com.example.newsaggregator.domain.model

data class NewsArticlesResult(
    val error: Errors? = null,
    val newsArticles: List<NewsArticle> = emptyList()
)