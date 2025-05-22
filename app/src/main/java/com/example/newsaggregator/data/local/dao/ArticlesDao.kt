package com.example.newsaggregator.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.newsaggregator.data.local.entity.ArticleEntity
import com.example.newsaggregator.data.local.entity.ArticleTagEntity
import com.example.newsaggregator.data.local.entity.ArticleWithTags
import kotlinx.coroutines.flow.Flow

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
    @Query("SELECT * FROM ${ArticleEntity.TABLE_NAME}")
    fun getAllArticlesWithTags(): Flow<List<ArticleWithTags>>


    @Query("DELETE FROM ${ArticleEntity.TABLE_NAME}")
    suspend fun deleteAll()
}