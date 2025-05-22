package com.example.newsaggregator.domain.repository

import com.example.newsaggregator.domain.model.Bookmark
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    suspend fun add(guid: String)
     fun getAll(): Flow<List<Bookmark>>
     fun isBookmarked(guid: String): Flow<Boolean>
    suspend fun delete(guid: String)
}