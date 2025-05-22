package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.model.NewsArticle
import kotlinx.coroutines.flow.Flow

interface GetAllNewsUseCase {
     fun execute(): Flow<List<NewsArticle>>
}