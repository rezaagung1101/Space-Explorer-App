package com.spaceexplorer.domain.repository

import androidx.lifecycle.LiveData
import com.spaceexplorer.domain.model.ApiResponse
import com.spaceexplorer.domain.model.RecentSearch
import retrofit2.Response

interface ArticleRepository {
    suspend fun getListArticles(): Response<ApiResponse>
    suspend fun searchArticlesByTitle(title: String): Response<ApiResponse>
    fun insertRecentSearch(query: String)
    fun deleteAllRecentSearches()
    fun getRecentSearches(): LiveData<List<RecentSearch>>
}