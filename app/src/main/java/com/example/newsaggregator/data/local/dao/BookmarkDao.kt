package com.example.newsaggregator.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.newsaggregator.data.local.entity.ArticleEntity
import com.example.newsaggregator.data.local.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM ${ArticleEntity.TABLE_NAME} WHERE ${ArticleEntity.GUID} = :guid")
    suspend fun getArticleByGuid(guid: String): ArticleEntity?

    @Insert(
        entity = BookmarkEntity::class,
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertBookmark(bookmark: BookmarkEntity)

    @Transaction
    suspend fun copyArticleToBookmark(guid: String) {
        val article = getArticleByGuid(guid) ?: return

        val bookmark = BookmarkEntity(
            guid = article.guid,
            title = article.title,
            description = article.description,
            imageUrl = article.imageUrl,
            author = article.author,
            date = article.date
        )

        insertBookmark(bookmark)
    }

    @Query("SELECT COUNT(*) FROM ${BookmarkEntity.TABLE_NAME} WHERE guid = :guid")
    fun isBookmarked(guid: String): Flow<Int>

    @Query("SELECT * FROM ${BookmarkEntity.TABLE_NAME}")
    fun getAll(): Flow<List<BookmarkEntity>>

    @Query("DELETE FROM ${BookmarkEntity.TABLE_NAME} WHERE guid = :guid")
    suspend fun delete(guid: String)
}