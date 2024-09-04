package com.spaceexplorer.data.source.remote.api

import com.spaceexplorer.domain.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v4/articles")
    suspend fun getListArticles( //non-blocking on UI Thread
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Response<ApiResponse>

    @GET("/v4/articles")
    suspend fun searchArticlesByTitle( //non-blocking on UI Thread
        @Query("title_contains") title: String
    ): Response<ApiResponse>

}