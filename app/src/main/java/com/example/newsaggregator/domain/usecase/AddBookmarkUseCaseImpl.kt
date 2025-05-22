package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.repository.BookmarkRepository
import javax.inject.Inject

class AddBookmarkUseCaseImpl @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) : AddBookmarkUseCase {
    override suspend fun execute(guid: String) {
        bookmarkRepository.add(guid = guid)
    }
}