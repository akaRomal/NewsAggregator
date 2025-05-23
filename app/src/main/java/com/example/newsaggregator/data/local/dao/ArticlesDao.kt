package com.example.newsaggregator.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.newsaggregator.data.local.entity.ArticleEntity
import com.example.newsaggregator.data.local.entity.ArticleSearchItem
import com.example.newsaggregator.data.local.entity.ArticleTagEntity
import com.example.newsaggregator.data.local.entity.ArticleWithTags

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTags(tags: List<ArticleTagEntity>)

    @Transaction
    suspend fun insertArticlesWithTags(news: List<ArticleWithTags>) {

        insertArticles(news.map { it.article })

        insertTags(news.flatMap { it.tags })
    }


    @Transaction
    @Query(
        "SELECT * FROM ${ArticleEntity.TABLE_NAME}" +
                " WHERE ${ArticleEntity.GUID}" +
                " IN (  SELECT ${ArticleTagEntity.ARTICLE_GUID}" +
                " FROM ${ArticleTagEntity.TABLE_NAME}" +
                " WHERE ${ArticleTagEntity.TAG} = :tag)"
    )
    fun searchArticlesWithTag(tag: String): List<ArticleWithTags>

    @Query("SELECT ${ArticleEntity.GUID} AS ${ArticleEntity.GUID}, " +
            "${ArticleEntity.TITLE} AS ${ArticleEntity.TITLE}, " +
            "${ArticleEntity.DESCRIPTION} AS ${ArticleEntity.DESCRIPTION} " +
            "FROM ${ArticleEntity.TABLE_NAME} " +
            "WHERE ${ArticleEntity.TITLE} LIKE ''%'' || UPPER(:query) || ''%'' OR ${ArticleEntity.DESCRIPTION} LIKE ''%'' || UPPER(:query) || ''%''")
    fun searchByQuery(query: String): List<ArticleSearchItem>

    @Transaction
    @Query("SELECT * FROM ${ArticleEntity.TABLE_NAME}")
    fun getAllArticlesWithTags(): List<ArticleWithTags>

    @Transaction
    @Query("SELECT * FROM ${ArticleEntity.TABLE_NAME} ORDER BY ${ArticleEntity.DATE} ASC")
    fun getAllArticlesWithTagsSortedByDateAsc(): List<ArticleWithTags>

    @Transaction
    @Query("SELECT * FROM ${ArticleEntity.TABLE_NAME} ORDER BY ${ArticleEntity.DATE} DESC")
    fun getAllArticlesWithTagsSortedByDateDesc(): List<ArticleWithTags>

    @Query("DELETE FROM ${ArticleEntity.TABLE_NAME}")
    suspend fun deleteAll()
}