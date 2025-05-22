package com.example.newsaggregator.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetAllTagsUseCase {
    suspend fun execute(): Flow<List<String>>
}