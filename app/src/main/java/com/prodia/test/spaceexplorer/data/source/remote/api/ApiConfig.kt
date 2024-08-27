package com.prodia.test.spaceexplorer.data.source.remote.api

import com.prodia.test.spaceexplorer.BuildConfig
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig { //for ApiService configuration and instantiation
    companion object {
        fun getApiService(): ApiService {
            // Define the certificate pinning
            val hash = BuildConfig.public_key_hash
            val spaceFlightUrl = BuildConfig.spaceflight_api_url
            val certificatePinner = CertificatePinner.Builder()
                .add(BuildConfig.spaceflight_api_url, "sha256/$hash")
                .build()

            // Build OkHttpClient with SSL Pinning
            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request()
                    val response = chain.proceed(request)
                    response
                }
                .certificatePinner(certificatePinner)
                .build()

            // Build Retrofit with OkHttpClient
            val retrofit = Retrofit.Builder()
                .baseUrl("https://$spaceFlightUrl")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}

//class ApiConfig { //for ApiService configuration and instantiation
//    val public_key_hash = "aCu9gReMs18ofcr9sqEY/0D9FdKAARMOd+CZMmhv4Jo="
//    companion object{
//        fun getApiService() : ApiService {
//            //build Retrofit by adding interceptors (based on debug mode) on the client
//            val loggingInterceptor = if(BuildConfig.DEBUG) {
//                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//            } else {
//                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
//            }
//            val certificatePinner = CertificatePinner.Builder()
//                .add("api.spaceflightnewsapi.net", "sha256/your_certificate_hash_here")
//                .build()
//            val client = OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
//                .build()
//            val retrofit = Retrofit.Builder()
//                .baseUrl(BuildConfig.spaceflight_api_url)
//                .addConverterFactory(GsonConverterFactory.create()) //Convert JSON to Java object
//                .client(client)
//                .build()
//            return retrofit.create(ApiService::class.java)
//        }
//    }
//
//}