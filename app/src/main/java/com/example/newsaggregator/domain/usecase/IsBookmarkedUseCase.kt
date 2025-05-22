package com.example.newsaggregator.domain.usecase

import kotlinx.coroutines.flow.Flow

interface IsBookmarkedUseCase {
    fun execute(guid: String): Flow<Boolean>
}