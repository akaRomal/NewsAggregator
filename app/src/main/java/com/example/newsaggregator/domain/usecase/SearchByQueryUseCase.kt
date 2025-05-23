package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.model.SearchResultItem

interface SearchByQueryUseCase {
    fun execute(query: String): List<SearchResultItem>
}