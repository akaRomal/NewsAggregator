package com.example.newsaggregator.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ArticleEntity.TABLE_NAME)
data class ArticleEntity(
    @PrimaryKey
    @ColumnInfo(name = GUID)
    val guid: String,

    @ColumnInfo(name = TITLE)
    val title: String,

    @ColumnInfo(name = DESCRIPTION)
    val description: String,

    @ColumnInfo(name = IMAGE_URL)
    val imageUrl: String,

    @ColumnInfo(name = AUTHOR)
    val author: String,

    @ColumnInfo(name = DATE)
    val date: String,
) {
    companion object {
        const val TABLE_NAME = "article"
        const val GUID = "guid"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val IMAGE_URL = "image_url"
        const val AUTHOR = "author"
        const val DATE = "date"
    }
}