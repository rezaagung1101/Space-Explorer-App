package com.prodia.test.spaceexplorer.di

import com.prodia.test.spaceexplorer.data.repository.ArticleRepositoryImpl
import com.prodia.test.spaceexplorer.data.source.local.db.ArticleDao
import com.prodia.test.spaceexplorer.data.source.remote.api.ApiService
import com.prodia.test.spaceexplorer.domain.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideArticleRepository(
        apiService: ApiService,
        dao: ArticleDao
    ): ArticleRepository {
        return ArticleRepositoryImpl(apiService, dao)
    }
}