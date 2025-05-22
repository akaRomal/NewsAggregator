package com.example.newsaggregator.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ArticleWithTags(
    @Embedded val article: ArticleEntity,
    @Relation(
        parentColumn = ArticleEntity.GUID,
        entityColumn = ArticleTagEntity.ARTICLE_GUID
    )
    val tags: List<ArticleTagEntity>
)