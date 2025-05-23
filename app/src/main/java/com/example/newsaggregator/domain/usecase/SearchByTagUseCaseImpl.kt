package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.model.NewsArticle
import com.example.newsaggregator.domain.repository.NewsRepository
import javax.inject.Inject

class SearchByTagUseCaseImpl @Inject constructor(private val newsRepository: NewsRepository) :
    SearchByTagUseCase {
    override fun execute(tag: String): List<NewsArticle> =
        newsRepository.searchArticlesWithTag(tag = tag)
}