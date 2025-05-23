package com.example.newsaggregator.domain.repository

import com.example.newsaggregator.domain.model.SearchResultItem

interface SearchRepository {
    fun search(query: String): List<SearchResultItem>
}