package com.spaceexplorer.data.di

import android.content.Context
import com.spaceexplorer.data.source.remote.api.ApiConfig
import com.spaceexplorer.data.source.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideCertificatePinner(): CertificatePinner {
        return ApiConfig.provideCertificatePinner()
    }

    @Provides
    @Singleton
    @Named("UserAgentInterceptor")
    fun provideUserAgentInterceptor(): Interceptor {
        return ApiConfig.provideUserAgentInterceptor()
    }

    @Provides
    @Singleton
    @Named("ChuckerInterceptor")
    fun provideChuckerInterceptor(
        @ApplicationContext context: Context,
    ): Interceptor {
        return ApiConfig.provideChuckerInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        certificatePinner: CertificatePinner,
        @Named("ChuckerInterceptor") chuckerInterceptor: Interceptor,
        @Named("UserAgentInterceptor") userAgentInterceptor: Interceptor,
    ): OkHttpClient {
        return ApiConfig.provideOkHttpClient(
            certificatePinner, chuckerInterceptor, userAgentInterceptor
        )
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return ApiConfig.provideRetrofit(okHttpClient)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
