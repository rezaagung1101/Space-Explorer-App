package com.spaceexplorer.data.source.remote.api

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.spaceexplorer.data.BuildConfig
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiConfig {
    fun provideOkHttpClient(
        certificatePinner: CertificatePinner,
        chuckerInterceptor: Interceptor, // add interceptor as parameter
        userAgentInterceptor: Interceptor,
    ): OkHttpClient {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(chuckerInterceptor) //add chucker interceptor
            .addInterceptor(userAgentInterceptor)
            .certificatePinner(certificatePinner)
            .build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val spaceFlightUrl = BuildConfig.spaceflight_api_url
        return Retrofit.Builder()
            .baseUrl("https://$spaceFlightUrl")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun provideCertificatePinner(): CertificatePinner {
        val hash = BuildConfig.public_key_hash
        val spaceFlightUrl = BuildConfig.spaceflight_api_url
        return CertificatePinner.Builder()
            .add(spaceFlightUrl, "sha256/$hash")
            .build()
    }

    fun provideChuckerInterceptor(context: Context): Interceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
    }

    fun provideUserAgentInterceptor() =
        Interceptor { chain ->
            val originalRequest: Request = chain.request()
            val requestWithUserAgent: Request = originalRequest.newBuilder()
                .header("User-Agent", "SpaceExplorer-App/1.0 (Prodia)")
                .build()
            chain.proceed(requestWithUserAgent)
        }

}


//class ApiConfig { //for ApiService configuration and instantiation
//    companion object {
//        fun getApiService(): ApiService {
//            // Define the certificate pinning
//            val hash = BuildConfig.public_key_hash
//            val spaceFlightUrl = BuildConfig.spaceflight_api_url
//            val certificatePinner = CertificatePinner.Builder()
//                .add(spaceFlightUrl, "sha256/$hash")
//                .build()
//
////            val loggingInterceptor = HttpLoggingInterceptor().apply {
////                level = HttpLoggingInterceptor.Level.BODY
////            }
////            val userAgentInterceptor = Interceptor { chain ->
////                val originalRequest: Request = chain.request()
////                val requestWithUserAgent: Request = originalRequest.newBuilder()
////                    .header("User-Agent", "SpaceExplorer-App/1.0 (CompanyA)")
////                    .build()
////                chain.proceed(requestWithUserAgent)
////            }
//
//            // Build OkHttpClient with SSL Pinning
//            val client = OkHttpClient.Builder()
//                .addInterceptor { chain ->
//                    val request = chain.request()
//                    val response = chain.proceed(request)
//                    response
//                }
//                .certificatePinner(certificatePinner)
//                .build()
//
//            // Build Retrofit with OkHttpClient
//            val retrofit = Retrofit.Builder()
//                .baseUrl("https://$spaceFlightUrl")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//
//            return retrofit.create(ApiService::class.java)
//        }
//    }
//}

