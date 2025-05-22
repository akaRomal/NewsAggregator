package com.example.newsaggregator.domain.repository

import com.example.newsaggregator.domain.model.NewsArticle
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun updateAll(query: String?)
    fun getAllNews(): Flow<List<NewsArticle>>
    fun getAllTags(): Flow<List<String>>
}