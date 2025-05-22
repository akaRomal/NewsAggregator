package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.model.Bookmark
import com.example.newsaggregator.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllBookmarksUseCaseImpl @Inject constructor(private val bookmarkRepository: BookmarkRepository) :
    GetAllBookmarksUseCase {
    override fun execute(): Flow<List<Bookmark>> = bookmarkRepository.getAll()
}