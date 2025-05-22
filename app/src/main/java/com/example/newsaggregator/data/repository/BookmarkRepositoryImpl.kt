package com.example.newsaggregator.data.repository

import android.util.Log
import com.example.newsaggregator.data.local.dao.BookmarkDao
import com.example.newsaggregator.data.mapper.Mapper
import com.example.newsaggregator.domain.model.Bookmark
import com.example.newsaggregator.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao,
    private val mapper: Mapper,
) : BookmarkRepository {

    override suspend fun add(guid: String) {
        bookmarkDao.copyArticleToBookmark(guid = guid)
    }

    override fun getAll(): Flow<List<Bookmark>> {
        return bookmarkDao.getAll().map {
            mapper.fromBookmarkToDomain(it)
        }
    }

    override fun isBookmarked(guid: String): Flow<Boolean> {
        Log.d("AAA", "BookmarkRepositoryImpl isBookmarked add start")
        return bookmarkDao.isBookmarked(guid = guid).map {
            Log.d("AAA", it.toString())
            it > 0
        }
    }

    override suspend fun delete(guid: String) {
        bookmarkDao.delete(guid = guid)
    }
}