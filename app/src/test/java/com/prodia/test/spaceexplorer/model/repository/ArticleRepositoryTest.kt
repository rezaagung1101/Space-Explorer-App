package com.prodia.test.spaceexplorer.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.prodia.test.spaceexplorer.model.api.ApiResponse
import com.prodia.test.spaceexplorer.model.api.ApiService
import com.prodia.test.spaceexplorer.model.data.RecentSearch
import com.prodia.test.spaceexplorer.model.db.ArticleDao
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import retrofit2.Response

class ArticleRepositoryTest {

    private lateinit var articleRepository: ArticleRepository
    private lateinit var apiService: ApiService
    private lateinit var articleDao: ArticleDao

    @Before
    fun setUp() {
        apiService = mock(ApiService::class.java)
        articleDao = mock(ArticleDao::class.java)
        articleRepository = ArticleRepository(apiService, articleDao)
    }

    @Test
    fun `test getListArticles returns successful response`() = runBlockingTest {
        val dummyApiResponse = ApiResponse(count = 1, next = null, previous = null, results = emptyList())
        `when`(apiService.getListArticles()).thenReturn(Response.success(dummyApiResponse))
        val response = articleRepository.getListArticles()
        assertEquals(true, response.isSuccessful)
        assertEquals(dummyApiResponse, response.body())
    }

    @Test
    fun `test searchArticlesByTitle returns successful response`() = runBlockingTest {
        val dummyApiResponse = ApiResponse(count = 1, next = null, previous = null, results = emptyList())
        `when`(apiService.searchArticlesByTitle("dummy title")).thenReturn(Response.success(dummyApiResponse))
        val response = articleRepository.searchArticlesByTitle("dummy title")
        assertEquals(true, response.isSuccessful)
        Assert.assertNotNull(response)
        assertEquals(dummyApiResponse, response.body())
    }

    @Test
    fun `test insertRecentSearch calls dao insert method`() = runBlocking {
        val query = "Indonesia"
        articleRepository.insertRecentSearch(query)
        verify(articleDao).insertRecentSearch(RecentSearch(query = query))
    }

    @Test
    fun `test deleteAllRecentSearches calls dao delete method`() = runBlocking {
        articleRepository.deleteAllRecentSearches()
        verify(articleDao).deleteAllRecentSearches()
    }

    @Test
    fun `test getRecentSearches returns LiveData from dao`() {
        val expectedLiveData: LiveData<List<RecentSearch>> = MutableLiveData(emptyList())
        `when`(articleDao.getRecentSearchList()).thenReturn(expectedLiveData)
        val result = articleRepository.getRecentSearches()
        Assert.assertNotNull(result)
        assertEquals(expectedLiveData, result)
    }
}
