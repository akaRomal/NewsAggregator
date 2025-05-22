package com.example.newsaggregator.data.mapper

import com.example.newsaggregator.data.local.entity.ArticleWithTags
import com.example.newsaggregator.data.remote.rss.dto.RssDto
import com.example.newsaggregator.domain.model.NewsArticle

interface Mapper {
    fun fromRssToDb(rssDto: RssDto): List<ArticleWithTags>
    fun fromArticleToDomain(articleWithTags: List<ArticleWithTags>): List<NewsArticle>
}