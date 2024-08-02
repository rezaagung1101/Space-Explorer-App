package com.prodia.test.spaceexplorer.model.repository

import com.prodia.test.spaceexplorer.model.api.ApiService

class ArticleRepository(private val apiService: ApiService) {
    suspend fun getListArticles() = apiService.getListArticles()
    suspend fun searchArticlesByTitle(title: String) = apiService.searchArticlesByTitle(title)
    suspend fun filterArticles(newsSite: String) = apiService.filterArticles(newsSite)
    suspend fun getNewsSites() = apiService.getNewsSites()
}