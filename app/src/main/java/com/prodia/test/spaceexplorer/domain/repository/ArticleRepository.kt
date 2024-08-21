package com.prodia.test.spaceexplorer.domain.repository

import androidx.lifecycle.LiveData
import com.prodia.test.spaceexplorer.data.source.remote.api.ApiService
import com.prodia.test.spaceexplorer.domain.model.RecentSearch
import com.prodia.test.spaceexplorer.data.source.local.db.ArticleDao
import com.prodia.test.spaceexplorer.data.source.remote.api.ApiResponse
import com.prodia.test.spaceexplorer.domain.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Response

interface ArticleRepository {
    suspend fun getListArticles(): Response<ApiResponse>
    suspend fun searchArticlesByTitle(title: String): Response<ApiResponse>
    fun insertRecentSearch(query: String)
    fun deleteAllRecentSearches()
    fun getRecentSearches(): LiveData<List<RecentSearch>>
}