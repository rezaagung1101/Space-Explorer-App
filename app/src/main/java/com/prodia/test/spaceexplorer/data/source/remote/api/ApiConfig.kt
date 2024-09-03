package com.prodia.test.spaceexplorer.data.source.remote.api

import com.prodia.test.spaceexplorer.BuildConfig
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiConfig { //for ApiService configuration and instantiation
    companion object{
        fun getApiService() : ApiService {
            //build Retrofit by adding interceptors (based on debug mode) on the client
            val hash = BuildConfig.public_key_hash
            val spaceFlightUrl = BuildConfig.spaceflight_api_url
            val certificatePinner = CertificatePinner.Builder()
                .add(spaceFlightUrl, "sha256/$hash")
                .build()

            val loggingInterceptor = if(BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }

            val userAgentInterceptor = Interceptor { chain ->
                val originalRequest: Request = chain.request()
                val requestWithUserAgent: Request = originalRequest.newBuilder()
                    .header("User-Agent", "SpaceExplorer-App/1.0 (Prodia)")
                    .build()
                chain.proceed(requestWithUserAgent)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(userAgentInterceptor) //add interceptor di sini
                .certificatePinner(certificatePinner) //ssl pining
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://$spaceFlightUrl")
                .addConverterFactory(GsonConverterFactory.create()) //Convert JSON to Java object
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
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

