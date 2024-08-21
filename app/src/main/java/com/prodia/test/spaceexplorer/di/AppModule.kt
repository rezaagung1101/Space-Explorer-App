package com.prodia.test.spaceexplorer.di

import android.content.Context
import com.prodia.test.spaceexplorer.data.repository.ArticleRepositoryImpl
import com.prodia.test.spaceexplorer.data.source.local.db.ArticleDao
import com.prodia.test.spaceexplorer.data.source.local.db.ArticleDatabase
import com.prodia.test.spaceexplorer.data.source.remote.api.ApiConfig
import com.prodia.test.spaceexplorer.data.source.remote.api.ApiService
import com.prodia.test.spaceexplorer.domain.repository.ArticleRepository
import com.prodia.test.spaceexplorer.domain.usecase.DeleteAllRecentSearchesUseCase
import com.prodia.test.spaceexplorer.domain.usecase.GetListArticlesUseCase
import com.prodia.test.spaceexplorer.domain.usecase.GetRecentSearchesUseCase
import com.prodia.test.spaceexplorer.domain.usecase.InsertRecentSearchUseCase
import com.prodia.test.spaceexplorer.domain.usecase.SearchArticlesByTitleUseCase
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

    @Provides
    @Singleton
    fun provideGetListArticlesUseCase(
        articleRepository: ArticleRepository
    ): GetListArticlesUseCase {
        return GetListArticlesUseCase(articleRepository)
    }

    @Provides
    @Singleton
    fun provideSearchArticlesByTitleUseCase(
        articleRepository: ArticleRepository
    ): SearchArticlesByTitleUseCase {
        return SearchArticlesByTitleUseCase(articleRepository)
    }

    @Provides
    @Singleton
    fun provideInsertRecentSearchUseCase(
        articleRepository: ArticleRepository
    ): InsertRecentSearchUseCase {
        return InsertRecentSearchUseCase(articleRepository)
    }

    @Provides
    @Singleton
    fun provideGetRecentSearchUseCase(
        articleRepository: ArticleRepository
    ): GetRecentSearchesUseCase{
        return GetRecentSearchesUseCase(articleRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteAllRecentSearchesUseCase(
        articleRepository: ArticleRepository
    ): DeleteAllRecentSearchesUseCase {
        return DeleteAllRecentSearchesUseCase(articleRepository)
    }

}
