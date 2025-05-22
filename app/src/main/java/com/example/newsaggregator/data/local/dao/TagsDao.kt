package com.example.newsaggregator.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.newsaggregator.data.local.entity.ArticleTagEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TagsDao {
    @Query("SELECT DISTINCT ${ArticleTagEntity.TAG} FROM ${ArticleTagEntity.TABLE_NAME}")
    fun getAll(): Flow<List<String>>
}