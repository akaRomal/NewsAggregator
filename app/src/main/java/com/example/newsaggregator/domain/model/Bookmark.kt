package com.example.newsaggregator.domain.model

data class Bookmark(
    val title: String,
    val description: String,
    val imageUrl: String,
    val articleUrl: String,
    val author: String,
    val date: String,
)