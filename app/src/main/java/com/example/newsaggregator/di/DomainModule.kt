package com.example.newsaggregator.di

import com.example.newsaggregator.domain.repository.BookmarkRepository
import com.example.newsaggregator.domain.repository.NewsRepository
import com.example.newsaggregator.domain.usecase.AddBookmarkUseCase
import com.example.newsaggregator.domain.usecase.AddBookmarkUseCaseImpl
import com.example.newsaggregator.domain.usecase.DeleteBookmarkUseCase
import com.example.newsaggregator.domain.usecase.DeleteBookmarkUseCaseImpl
import com.example.newsaggregator.domain.usecase.GetAllBookmarksUseCase
import com.example.newsaggregator.domain.usecase.GetAllBookmarksUseCaseImpl
import com.example.newsaggregator.domain.usecase.GetAllNewsUseCase
import com.example.newsaggregator.domain.usecase.GetAllNewsUseCaseImpl
import com.example.newsaggregator.domain.usecase.GetAllTagsUseCase
import com.example.newsaggregator.domain.usecase.GetAllTagsUseCaseImpl
import com.example.newsaggregator.domain.usecase.IsBookmarkedUseCase
import com.example.newsaggregator.domain.usecase.IsBookmarkedUseCaseImpl
import com.example.newsaggregator.domain.usecase.UpdateNewsUseCase
import com.example.newsaggregator.domain.usecase.UpdateNewsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideUpdateNewsUseCase(newsRepository: NewsRepository): UpdateNewsUseCase {
        return UpdateNewsUseCaseImpl(
            newsRepository = newsRepository
        )
    }

    @Singleton
    @Provides
    fun provideGetAllNewsUseCase(newsRepository: NewsRepository): GetAllNewsUseCase {
        return GetAllNewsUseCaseImpl(
            newsRepository = newsRepository
        )
    }

    @Singleton
    @Provides
    fun providesGetAllTagsUseCase(newsRepository: NewsRepository): GetAllTagsUseCase {
        return GetAllTagsUseCaseImpl(
            newsRepository = newsRepository
        )
    }

    @Singleton
    @Provides
    fun providesAddBookmarkUseCase(bookmarkRepository: BookmarkRepository): AddBookmarkUseCase {
        return AddBookmarkUseCaseImpl(
            bookmarkRepository = bookmarkRepository
        )
    }

    @Singleton
    @Provides
    fun providesDeleteBookmarkUseCase(bookmarkRepository: BookmarkRepository): DeleteBookmarkUseCase {
        return DeleteBookmarkUseCaseImpl(
            bookmarkRepository = bookmarkRepository
        )
    }

    @Singleton
    @Provides
    fun providesIsBookmarkedUseCase(bookmarkRepository: BookmarkRepository): IsBookmarkedUseCase {
        return IsBookmarkedUseCaseImpl(
            bookmarkRepository = bookmarkRepository
        )
    }

    @Singleton
    @Provides
    fun providesGetAllBookmarksUseCase(bookmarkRepository: BookmarkRepository): GetAllBookmarksUseCase {
        return GetAllBookmarksUseCaseImpl(
            bookmarkRepository = bookmarkRepository
        )
    }
}