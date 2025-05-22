package com.example.newsaggregator.presentation.mapper

import com.example.newsaggregator.domain.model.NewsArticle
import com.example.newsaggregator.presentation.screens.articles.model.NewsArticleItem

interface Mapper {
    fun fromNewsArticleToNewsArticleItem(newsItems: List<NewsArticle>): List<NewsArticleItem>
}