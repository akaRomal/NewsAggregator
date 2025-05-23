package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.model.NewsArticle
import com.example.newsaggregator.domain.repository.NewsRepository
import javax.inject.Inject

class SortedByDateUseCaseImpl @Inject constructor(private val newsRepository: NewsRepository) :
    SortedByDateUseCase {
    override fun execute(sortedAsc: Boolean): List<NewsArticle> =
        newsRepository.fetchArticlesSortedByDate(sortedAsc = sortedAsc)
}