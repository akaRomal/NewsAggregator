package com.example.newsaggregator.data.repository

import com.example.newsaggregator.data.local.dao.ArticlesDao
import com.example.newsaggregator.domain.model.SearchResultItem
import com.example.newsaggregator.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val articlesDao: ArticlesDao) :
    SearchRepository {
    override fun search(query: String): List<SearchResultItem> {
        return articlesDao.searchByQuery(query = query).map {
            SearchResultItem(
                guid = it.guid,
                title = it.title,
                description = it.title
            )
        }
    }
}