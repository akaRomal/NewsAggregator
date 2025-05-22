package com.example.newsaggregator.domain.usecase

interface AddBookmarkUseCase {
    suspend fun execute(guid: String)
}