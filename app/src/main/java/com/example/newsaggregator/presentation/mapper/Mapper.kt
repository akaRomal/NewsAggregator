package com.example.newsaggregator.presentation.mapper

import com.example.newsaggregator.domain.model.Bookmark
import com.example.newsaggregator.domain.model.NewsArticle
import com.example.newsaggregator.presentation.model.BookmarkItem
import com.example.newsaggregator.presentation.model.NewsArticleItem

interface Mapper {
    fun fromNewsArticleToNewsArticleItem(newsItems: List<NewsArticle>): List<NewsArticleItem>
    fun fromBookmarkToBookmarkItem(newsItems: List<Bookmark>): List<BookmarkItem>
}