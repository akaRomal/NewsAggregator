package com.example.newsaggregator.domain.repository

import com.example.newsaggregator.domain.model.NewsArticle
import com.example.newsaggregator.domain.model.NewsArticlesResult
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getAll(): NewsArticlesResult
    fun getAllTags(): Flow<List<String>>
    fun fetchArticlesSortedByDate(sortedAsc: Boolean): List<NewsArticle>
    fun searchArticlesWithTag(tag: String): List<NewsArticle>
}