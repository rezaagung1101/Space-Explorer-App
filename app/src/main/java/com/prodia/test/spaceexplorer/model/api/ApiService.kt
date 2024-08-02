package com.prodia.test.spaceexplorer.model.api

import com.prodia.test.spaceexplorer.model.data.Article
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("articles")
    suspend fun getListArticles(): List<Article>

    @GET("articles")
    suspend fun searchArticlesByTitle(@Query("title_contains") title: String): List<Article>

    @GET("articles")
    suspend fun filterArticles(@Query("news_site") newsSite: String): List<Article>

    @GET("news_sites")
    suspend fun getNewsSites(): List<String>
}