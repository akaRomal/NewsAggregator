package com.example.newsaggregator.di

import android.content.Context
import androidx.room.Room
import com.example.newsaggregator.BuildConfig
import com.example.newsaggregator.data.local.dao.AppDatabase
import com.example.newsaggregator.data.local.dao.ArticlesDao
import com.example.newsaggregator.data.local.dao.BookmarkDao
import com.example.newsaggregator.data.local.dao.TagsDao
import com.example.newsaggregator.data.mapper.Mapper
import com.example.newsaggregator.data.mapper.MapperImpl
import com.example.newsaggregator.data.remote.rss.RssFeed
import com.example.newsaggregator.data.repository.BookmarkRepositoryImpl
import com.example.newsaggregator.data.repository.NewsRepositoryImpl
import com.example.newsaggregator.domain.repository.BookmarkRepository
import com.example.newsaggregator.domain.repository.NewsRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nl.adaptivity.xmlutil.serialization.XML
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        rssFeed: RssFeed,
        mapper: Mapper,
        articlesDao: ArticlesDao,
        tagsDao: TagsDao,
    ): NewsRepository {
        return NewsRepositoryImpl(
            rssFeed = rssFeed,
            mapper = mapper,
            articlesDao = articlesDao,
            tagsDao = tagsDao
        )
    }


    @Singleton
    @Provides
    fun provideBookmarkRepository(
        bookmarkDao: BookmarkDao,
        mapper: Mapper,
    ): BookmarkRepository {
        return BookmarkRepositoryImpl(
            bookmarkDao = bookmarkDao,
            mapper = mapper
        )
    }

    @Singleton
    @Provides
    fun provideMapper(): Mapper {
        return MapperImpl()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.BASIC
            }
        }
    }

    @Provides
    @Singleton
    fun provideDaDataOkHttpClient(
        loggingInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(BuildConfig.TIMEOUT_CONNECT, TimeUnit.MILLISECONDS)
            .writeTimeout(BuildConfig.TIMEOUT_WRITE, TimeUnit.MILLISECONDS)
            .readTimeout(BuildConfig.TIMEOUT_READ, TimeUnit.MILLISECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideServer1Retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_API_SERVER)
            .client(okHttpClient)
            .addConverterFactory(
                XML.asConverterFactory(
                    "application/xml; charset=UTF8".toMediaType()
                )
            ).build()
    }

    @Singleton
    @Provides
    fun provideRssFeed(retrofit: Retrofit): RssFeed {
        return retrofit.create(RssFeed::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            "database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideArticlesDao(appDatabase: AppDatabase): ArticlesDao {
        return appDatabase.articlesDao()
    }

    @Singleton
    @Provides
    fun provideTagsDao(appDatabase: AppDatabase): TagsDao {
        return appDatabase.tagsDao()
    }

    @Singleton
    @Provides
    fun provideBookmarkDao(appDatabase: AppDatabase): BookmarkDao {
        return appDatabase.bookmarkDao()
    }
}