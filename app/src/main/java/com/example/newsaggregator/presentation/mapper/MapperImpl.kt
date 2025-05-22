package com.example.newsaggregator.presentation.mapper

import com.example.newsaggregator.domain.model.Bookmark
import com.example.newsaggregator.domain.model.NewsArticle
import com.example.newsaggregator.presentation.model.BookmarkItem
import com.example.newsaggregator.presentation.model.NewsArticleItem

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

    override fun fromBookmarkToBookmarkItem(bookmark: List<Bookmark>): List<BookmarkItem> {
        return bookmark.map {
            BookmarkItem(
                title = it.title,
                description = it.description,
                imageUrl = it.imageUrl,
                articleUrl = it.articleUrl,
                author = it.author,
                date = it.date
            )
        }
    }
}