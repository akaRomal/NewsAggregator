package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsBookmarkedUseCaseImpl @Inject constructor(private val bookmarkRepository: BookmarkRepository) :
    IsBookmarkedUseCase {
    override fun execute(guid: String): Flow<Boolean> = bookmarkRepository.isBookmarked(guid = guid)
}