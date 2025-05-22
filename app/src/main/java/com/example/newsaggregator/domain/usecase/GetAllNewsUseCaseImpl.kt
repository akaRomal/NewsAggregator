package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.model.NewsArticle
import com.example.newsaggregator.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNewsUseCaseImpl @Inject constructor(private val newsRepository: NewsRepository) :
    GetAllNewsUseCase {
    override fun execute(): Flow<List<NewsArticle>> = newsRepository.getAllNews()
}