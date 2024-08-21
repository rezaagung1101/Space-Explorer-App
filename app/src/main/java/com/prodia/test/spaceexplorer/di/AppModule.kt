package com.prodia.test.spaceexplorer.di

import android.content.Context
import com.prodia.test.spaceexplorer.data.repository.ArticleRepositoryImpl
import com.prodia.test.spaceexplorer.data.source.local.db.ArticleDao
import com.prodia.test.spaceexplorer.data.source.local.db.ArticleDatabase
import com.prodia.test.spaceexplorer.data.source.remote.api.ApiConfig
import com.prodia.test.spaceexplorer.data.source.remote.api.ApiService
import com.prodia.test.spaceexplorer.domain.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiConfig.getApiService()
    }

    @Provides
    @Singleton
    fun provideArticleDao(appDatabase: ArticleDatabase): ArticleDao {
        return appDatabase.articleDao()
    }

    @Provides
    @Singleton
    fun provideArticleDatabase(@ApplicationContext context: Context): ArticleDatabase {
        return ArticleDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideArticleRepository(
        apiService: ApiService,
        dao: ArticleDao
    ): ArticleRepository {
        return ArticleRepositoryImpl(apiService, dao)
    }
}
