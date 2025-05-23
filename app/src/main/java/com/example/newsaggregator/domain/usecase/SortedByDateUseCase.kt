package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.model.NewsArticle

interface SortedByDateUseCase {
    fun execute(sortedAsc: Boolean): List<NewsArticle>
}