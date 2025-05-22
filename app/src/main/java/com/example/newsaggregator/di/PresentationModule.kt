package com.example.newsaggregator.di


import com.example.newsaggregator.presentation.mapper.Mapper
import com.example.newsaggregator.presentation.mapper.MapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @MapperPresentation
    @Singleton
    @Provides
    fun provideMapper(): Mapper {
        return MapperImpl()
    }
}