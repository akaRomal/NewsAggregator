package com.example.newsaggregator.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = ArticleTagEntity.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ArticleEntity::class,
            parentColumns = [ArticleEntity.GUID],
            childColumns = [ArticleTagEntity.ARTICLE_GUID],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = [ArticleTagEntity.ARTICLE_GUID, ArticleTagEntity.TAG], unique = true),
        Index(value = [ArticleTagEntity.ARTICLE_GUID]),
        Index(value = [ArticleTagEntity.TAG])
    ]
)
data class ArticleTagEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Int? = null,

    @ColumnInfo(name = ARTICLE_GUID)
    val articleGuid: String,

    @ColumnInfo(name = TAG)
    val tag: String
) {
    companion object {
        const val TABLE_NAME = "article_tag"
        const val ID = "_id"
        const val ARTICLE_GUID = "article_guid"
        const val TAG = "tag"
    }
}