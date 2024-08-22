package com.prodia.test.spaceexplorer.di

import com.prodia.test.spaceexplorer.data.source.remote.api.ApiConfig
import com.prodia.test.spaceexplorer.data.source.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiConfig.getApiService()
    }
}