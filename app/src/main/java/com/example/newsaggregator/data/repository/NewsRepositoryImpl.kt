package com.example.newsaggregator.data.repository

import com.example.newsaggregator.data.local.dao.ArticlesDao
import com.example.newsaggregator.data.local.dao.TagsDao
import com.example.newsaggregator.data.local.entity.ArticleWithTags
import com.example.newsaggregator.data.mapper.Mapper
import com.example.newsaggregator.data.remote.rss.RssFeed
import com.example.newsaggregator.domain.model.Errors
import com.example.newsaggregator.domain.model.NewsArticle
import com.example.newsaggregator.domain.model.NewsArticlesResult
import com.example.newsaggregator.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val rssFeed: RssFeed,
    private val mapper: Mapper,
    private val articlesDao: ArticlesDao,
    private val tagsDao: TagsDao,
) : NewsRepository {

    override suspend fun getAll(): NewsArticlesResult {
        var error: Errors? = null
        try {
            val data = rssFeed.getRss()
            val news = mapper.fromRssToDb(data)
            articlesDao.deleteAll()
            addNewsDb(news)
        } catch (_: HttpException) {
            error = Errors.HTTP
        } catch (_: SocketTimeoutException) {
            error = Errors.TIMEOUT
        } catch (_: IOException) {
            error = Errors.NETWORK
        } catch (_: Exception) {
            error = Errors.UNKNOWN
        }
        val articles = getAllNews()
        return NewsArticlesResult(
            error = error,
            newsArticles = articles
        )
    }

    override fun getAllTags(): Flow<List<String>> {
        return tagsDao.getAll()
    }

    override fun fetchArticlesSortedByDate(sortedAsc: Boolean): List<NewsArticle> {
        return with(mapper) {
            fromArticleToDomain(
                if (sortedAsc) articlesDao.getAllArticlesWithTagsSortedByDateDesc()
                else articlesDao.getAllArticlesWithTagsSortedByDateAsc()
            )
        }
    }

    override fun searchArticlesWithTag(tag: String): List<NewsArticle> {
        return with(mapper) {
            fromArticleToDomain(articlesDao.searchArticlesWithTag(tag = tag))
        }
    }

    private fun getAllNews(): List<NewsArticle> {
        return with(mapper) {
            fromArticleToDomain(articlesDao.getAllArticlesWithTags())
        }
    }

    private suspend fun addNewsDb(articleWithTags: List<ArticleWithTags>) {
        articlesDao.insertArticlesWithTags(
            news = articleWithTags
        )
    }
}