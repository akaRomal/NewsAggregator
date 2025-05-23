package com.example.newsaggregator.di

import com.example.newsaggregator.domain.repository.BookmarkRepository
import com.example.newsaggregator.domain.repository.NewsRepository
import com.example.newsaggregator.domain.usecase.AddBookmarkUseCase
import com.example.newsaggregator.domain.usecase.AddBookmarkUseCaseImpl
import com.example.newsaggregator.domain.usecase.DeleteBookmarkUseCase
import com.example.newsaggregator.domain.usecase.DeleteBookmarkUseCaseImpl
import com.example.newsaggregator.domain.usecase.GetAllBookmarksUseCase
import com.example.newsaggregator.domain.usecase.GetAllBookmarksUseCaseImpl
import com.example.newsaggregator.domain.usecase.GetAllTagsUseCase
import com.example.newsaggregator.domain.usecase.GetAllTagsUseCaseImpl
import com.example.newsaggregator.domain.usecase.GetNewsUseCase
import com.example.newsaggregator.domain.usecase.GetNewsUseCaseImpl
import com.example.newsaggregator.domain.usecase.IsBookmarkedUseCase
import com.example.newsaggregator.domain.usecase.IsBookmarkedUseCaseImpl
import com.example.newsaggregator.domain.usecase.SearchByTagUseCase
import com.example.newsaggregator.domain.usecase.SearchByTagUseCaseImpl
import com.example.newsaggregator.domain.usecase.SortedByDateUseCase
import com.example.newsaggregator.domain.usecase.SortedByDateUseCaseImpl
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
    fun provideGetNewsUseCase(newsRepository: NewsRepository): GetNewsUseCase {
        return GetNewsUseCaseImpl(
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
    fun providesSortedByDateUseCaseUseCase(newsRepository: NewsRepository): SortedByDateUseCase {
        return SortedByDateUseCaseImpl(
            newsRepository = newsRepository
        )
    }

    @Singleton
    @Provides
    fun providesSearchByTagUseCase(newsRepository: NewsRepository): SearchByTagUseCase {
        return SearchByTagUseCaseImpl(
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