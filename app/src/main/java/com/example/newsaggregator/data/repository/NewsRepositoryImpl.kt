package com.example.newsaggregator.data.repository

import com.example.newsaggregator.data.local.dao.ArticlesDao
import com.example.newsaggregator.data.local.dao.TagsDao
import com.example.newsaggregator.data.local.entity.ArticleWithTags
import com.example.newsaggregator.data.mapper.Mapper
import com.example.newsaggregator.data.remote.rss.RssFeed
import com.example.newsaggregator.domain.model.NewsArticle
import com.example.newsaggregator.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val rssFeed: RssFeed,
    private val mapper: Mapper,
    private val articlesDao: ArticlesDao,
    private val tagsDao: TagsDao,
) : NewsRepository {

    override suspend fun updateAll(query: String?) {
        val data = rssFeed.getRss(query = query ?: "international")
        val news = mapper.fromRssToDb(data)
        articlesDao.deleteAll()
        addNewsDb(news)
    }

    override fun getAllNews(): Flow<List<NewsArticle>> {
        return articlesDao
            .getAllArticlesWithTags()
            .map { mapper.fromArticleToDomain(it) }

    }

    override fun getAllTags(): Flow<List<String>> {
        return tagsDao.getAll()
    }

    private suspend fun addNewsDb(articleWithTags: List<ArticleWithTags>) {
        articlesDao.insertArticlesWithTags(
            news = articleWithTags
        )
    }
}