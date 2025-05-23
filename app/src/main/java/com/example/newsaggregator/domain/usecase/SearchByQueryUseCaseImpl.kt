package com.example.newsaggregator.domain.usecase

import com.example.newsaggregator.domain.model.SearchResultItem
import com.example.newsaggregator.domain.repository.SearchRepository
import javax.inject.Inject

class SearchByQueryUseCaseImpl @Inject constructor(private val searchRepository: SearchRepository) :
    SearchByQueryUseCase {
    override fun execute(query: String): List<SearchResultItem> {
       return searchRepository.search(query = query)
    }
}