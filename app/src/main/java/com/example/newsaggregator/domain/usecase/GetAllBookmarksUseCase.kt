package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.model.Bookmark
import kotlinx.coroutines.flow.Flow

interface GetAllBookmarksUseCase {
    fun execute(): Flow<List<Bookmark>>
}