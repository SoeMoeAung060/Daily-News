package com.soe.dailynews.di.articleModule

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.soe.dailynews.data.local.NewsTypeConverter
import com.soe.dailynews.data.local.dao.ArticleDao
import com.soe.dailynews.data.local.database.NewsDatabase
import com.soe.dailynews.data.remote.api.NewsApi
import com.soe.dailynews.data.repository.ArticleRepositoryImpl
import com.soe.dailynews.domain.repository.ArticleRepository
import com.soe.dailynews.domain.usecase.ArticlesUseCase
import com.soe.dailynews.domain.usecase.GetBreakingNews
import com.soe.dailynews.domain.usecase.GetEverythingNews
import com.soe.dailynews.util.BASE_URL
import com.soe.dailynews.util.NEWS_DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()


        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(NewsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsTypeConverter(): NewsTypeConverter = NewsTypeConverter()

    @Provides
    @Singleton
    fun provideNewsDatabase(
        @ApplicationContext context: Context
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = context.applicationContext,
            klass = NewsDatabase::class.java,
            name = NEWS_DB_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): ArticleDao = newsDatabase.articleDao()


    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi : NewsApi,
        newsDatabase: NewsDatabase
    ) : ArticleRepository = ArticleRepositoryImpl(newsApi, newsDatabase)


    @Provides
    @Singleton
    fun provideNewsUseCase(
        articleRepository: ArticleRepository
    ) : ArticlesUseCase {
        return ArticlesUseCase(
            getEverythingNews = GetEverythingNews(articleRepository),
            getBreakingNews = GetBreakingNews(articleRepository)
        )
    }

}