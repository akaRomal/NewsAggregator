package com.example.newsaggregator.domain.usecase

interface UpdateNewsUseCase {
    suspend fun execute(query: String?)
}