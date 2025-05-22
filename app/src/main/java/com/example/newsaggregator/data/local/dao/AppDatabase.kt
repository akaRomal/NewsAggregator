package com.example.newsaggregator.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsaggregator.data.local.entity.ArticleEntity
import com.example.newsaggregator.data.local.entity.ArticleTagEntity

@Database(entities = [ArticleEntity::class, ArticleTagEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticlesDao
    abstract fun tagsDao(): TagsDao
}