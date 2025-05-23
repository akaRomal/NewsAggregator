package com.example.newsaggregator.data.local.entity

import androidx.room.ColumnInfo

data class ArticleSearchItem(
    @ColumnInfo(name = GUID)
    val guid: String,

    @ColumnInfo(name = TITLE)
    val title: String,

    @ColumnInfo(name = DESCRIPTION)
    val description: String
){
    companion object {
        const val GUID = ArticleEntity.GUID
        const val TITLE = ArticleEntity.TITLE
        const val DESCRIPTION = ArticleEntity.DESCRIPTION
    }
}