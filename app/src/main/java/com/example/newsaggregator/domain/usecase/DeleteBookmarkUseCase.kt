package com.example.newsaggregator.domain.usecase

interface DeleteBookmarkUseCase {
    suspend fun execute(guid: String)
}