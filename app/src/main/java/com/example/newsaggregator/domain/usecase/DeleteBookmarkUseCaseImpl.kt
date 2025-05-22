package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.repository.BookmarkRepository

class DeleteBookmarkUseCaseImpl(private val bookmarkRepository: BookmarkRepository) :
    DeleteBookmarkUseCase {
    override suspend fun execute(guid: String) {
        bookmarkRepository.delete(guid = guid)
    }
}