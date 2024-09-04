package com.spaceexplorer.data.repository


import androidx.lifecycle.LiveData
import com.spaceexplorer.data.source.local.db.ArticleDao
import com.spaceexplorer.data.source.remote.api.ApiService
import com.spaceexplorer.domain.model.ApiResponse
import com.spaceexplorer.domain.model.RecentSearch
import com.spaceexplorer.domain.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class ArticleRepositoryImpl(
    private val apiService: ApiService,
    private val dao: ArticleDao
) : ArticleRepository {

    override suspend fun getListArticles(): Response<ApiResponse> = apiService.getListArticles()

    override suspend fun searchArticlesByTitle(title: String): Response<ApiResponse> = apiService.searchArticlesByTitle(title)

    override fun insertRecentSearch(query: String): Unit = runBlocking {
        this.launch(Dispatchers.IO) {
            dao.insertRecentSearch(RecentSearch(query = query))
        }
    }

    override fun deleteAllRecentSearches(): Unit = runBlocking {
        this.launch(Dispatchers.IO) {
            dao.deleteAllRecentSearches()
        }
    }

    override fun getRecentSearches(): LiveData<List<RecentSearch>> {
        return dao.getRecentSearchList()
    }
}