package com.prodia.test.spaceexplorer.data.source.remote.api

import com.prodia.test.spaceexplorer.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig { //for ApiService configuration and instantiation
    companion object{
        fun getApiService() : ApiService {
            //build Retrofit by adding interceptors (based on debug mode) on the client
            val loggingInterceptor = if(BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.spaceflight_api_url)
                .addConverterFactory(GsonConverterFactory.create()) //Convert JSON to Java object
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

}