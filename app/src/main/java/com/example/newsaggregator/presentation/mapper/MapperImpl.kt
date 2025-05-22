package com.example.newsaggregator.presentation.mapper

import com.example.newsaggregator.domain.model.NewsArticle
import com.example.newsaggregator.presentation.screens.articles.model.NewsArticleItem

class MapperImpl : Mapper {
    override fun fromNewsArticleToNewsArticleItem(newsItems: List<NewsArticle>): List<NewsArticleItem> {
        return newsItems.map {
            NewsArticleItem(
                title = it.title,
                description = it.description,
                imageUrl = it.imageUrl,
                articleUrl = it.articleUrl,
                tag = it.tags,
                author = it.author,
                date = it.date
            )
        }
    }
}