package com.prodia.test.spaceexplorer.model.repository

import androidx.lifecycle.LiveData
import com.prodia.test.spaceexplorer.model.api.ApiService
import com.prodia.test.spaceexplorer.model.data.RecentSearch
import com.prodia.test.spaceexplorer.model.db.ArticleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ArticleRepository(private val apiService: ApiService, private val dao: ArticleDao) {
    suspend fun getListArticles() = apiService.getListArticles()
    suspend fun searchArticlesByTitle(title: String) = apiService.searchArticlesByTitle(title)

    fun insertRecentSearch(query: String) = runBlocking {
        this.launch(Dispatchers.IO) {
            dao.insert(RecentSearch(query = query))
        }
    }

    fun deleteAllRecentSearches() = runBlocking {
        this.launch(Dispatchers.IO) {
            dao.deleteAllRecentSearches()
        }
    }
    fun getRecentSearches(): LiveData<List<RecentSearch>> {
        return dao.getRecentSearchList()
    }
}