package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.model.NewsArticle

interface SearchByTagUseCase {
    fun execute(tag: String): List<NewsArticle>
}