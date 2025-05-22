package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTagsUseCaseImpl @Inject constructor(private val newsRepository: NewsRepository) :
    GetAllTagsUseCase {
    override suspend fun execute(): Flow<List<String>> = newsRepository.getAllTags()
}