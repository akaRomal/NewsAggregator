package com.example.newsaggregator.data.mapper

import com.example.newsaggregator.data.local.entity.ArticleEntity
import com.example.newsaggregator.data.local.entity.ArticleTagEntity
import com.example.newsaggregator.data.local.entity.ArticleWithTags
import com.example.newsaggregator.data.local.entity.BookmarkEntity
import com.example.newsaggregator.data.remote.rss.dto.ItemDto
import com.example.newsaggregator.data.remote.rss.dto.RssDto
import com.example.newsaggregator.domain.model.Bookmark
import com.example.newsaggregator.domain.model.NewsArticle
import org.jsoup.Jsoup
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class MapperImpl : Mapper {

    override fun fromRssToDb(rssDto: RssDto): List<ArticleWithTags> =
        rssDto.channel.items.map { it.toArticleWithTags() }

    override fun fromArticleToDomain(articlesWithTags: List<ArticleWithTags>): List<NewsArticle> {
        return articlesWithTags.map { item ->
            NewsArticle(
                title = item.article.title,
                description = item.article.description,
                imageUrl = item.article.imageUrl,
                articleUrl = item.article.guid,
                author = item.article.author,
                date = item.article.date,
                tags = item.tags.map { it.tag }
            )
        }
    }

    override fun fromBookmarkToDomain(bookmarkEntity: List<BookmarkEntity>): List<Bookmark> {
        return bookmarkEntity.map { item ->
            Bookmark(
                title = item.title,
                description = item.description,
                imageUrl = item.imageUrl,
                articleUrl = item.guid,
                author = item.author,
                date = item.date,
            )
        }
    }

    private fun String.getDescription(): String {
        return Jsoup.parse(this).selectFirst("p")?.text()?.trim().orEmpty()
    }

    private fun ItemDto.toArticleWithTags() = ArticleWithTags(
        article = toArticleEntity(),
        tags = toTagEntities()
    )

    private fun ItemDto.toArticleEntity() = ArticleEntity(
        guid = guid,
        title = title,
        description = description.getDescription(),
        imageUrl = contents.firstOrNull()?.url.orEmpty(),
        author = dcCreator,
        date = pubDate.formatedDate()
    )

    private fun ItemDto.toTagEntities(): List<ArticleTagEntity> {
        return categories.map { category ->
            ArticleTagEntity(
                articleGuid = guid,
                tag = category.value
            )
        }
    }

    private fun String.formatedDate(): String {
        val parser = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH).apply {
            timeZone = TimeZone.getTimeZone("GMT")
        }
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        return try {
            val date: Date? = parser.parse(this)
            if (date != null) formatter.format(date) else this
        } catch (_: ParseException) {
            this
        }
    }
}