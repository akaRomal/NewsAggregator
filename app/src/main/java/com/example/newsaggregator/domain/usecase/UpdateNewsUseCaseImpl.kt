package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.repository.NewsRepository
import javax.inject.Inject

class UpdateNewsUseCaseImpl @Inject constructor(private val newsRepository: NewsRepository) :
    UpdateNewsUseCase {
    override suspend fun execute(query: String?) {
        return newsRepository.updateAll(query = query)
    }
}