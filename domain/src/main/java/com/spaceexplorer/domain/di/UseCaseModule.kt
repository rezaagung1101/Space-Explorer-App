package com.spaceexplorer.domain.di


import com.spaceexplorer.domain.repository.ArticleRepository
import com.spaceexplorer.domain.usecase.articles.GetListArticlesUseCase
import com.spaceexplorer.domain.usecase.articles.SearchArticlesByTitleUseCase
import com.spaceexplorer.domain.usecase.recentSearch.DeleteAllRecentSearchesUseCase
import com.spaceexplorer.domain.usecase.recentSearch.GetRecentSearchesUseCase
import com.spaceexplorer.domain.usecase.recentSearch.InsertRecentSearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

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
    ): GetRecentSearchesUseCase {
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
