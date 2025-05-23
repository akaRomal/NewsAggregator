package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.model.NewsArticlesResult

interface GetNewsUseCase {
    suspend fun execute(): NewsArticlesResult
}