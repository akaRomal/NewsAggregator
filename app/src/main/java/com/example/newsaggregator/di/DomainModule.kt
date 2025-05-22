package com.example.newsaggregator.di

import com.example.newsaggregator.domain.repository.NewsRepository
import com.example.newsaggregator.domain.usecase.GetAllNewsUseCase
import com.example.newsaggregator.domain.usecase.GetAllNewsUseCaseImpl
import com.example.newsaggregator.domain.usecase.GetAllTagsUseCase
import com.example.newsaggregator.domain.usecase.GetAllTagsUseCaseImpl
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
}