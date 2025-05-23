package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.model.NewsArticlesResult
import com.example.newsaggregator.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCaseImpl @Inject constructor(private val newsRepository: NewsRepository) :
    GetNewsUseCase {
    override suspend fun execute(): NewsArticlesResult =
        newsRepository.getAll()
}