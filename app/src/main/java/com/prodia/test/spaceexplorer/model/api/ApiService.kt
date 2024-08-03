package com.prodia.test.spaceexplorer.model.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("articles")
    suspend fun getListArticles(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Response<ApiResponse>
    @GET("articles")
    suspend fun searchArticlesByTitle(
        @Query("title_contains") title: String
    ): Response<ApiResponse>


}