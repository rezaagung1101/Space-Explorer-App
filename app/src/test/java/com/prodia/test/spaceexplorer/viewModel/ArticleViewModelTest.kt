package com.prodia.test.spaceexplorer.viewModel

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.prodia.test.spaceexplorer.model.api.ApiResponse
import com.prodia.test.spaceexplorer.model.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import retrofit2.Response

class ArticleViewModelTest {

    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var articleRepository: ArticleRepository
    private lateinit var articleViewModel: ArticleViewModel
    private val dummyResponse = DummyData.getDummyResponse()

    private val testDispatcher = TestCoroutineDispatcher()


    @Before
    fun setUp() {
        articleRepository = mock(ArticleRepository::class.java)
        articleViewModel = ArticleViewModel(articleRepository)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test getListArticles updates articles LiveData`() = runTest {
        `when`(articleRepository.getListArticles()).thenReturn(dummyResponse)
        articleViewModel.getListArticles()
        advanceUntilIdle()
        val actualResult = articleViewModel.articles.value
        val expectedResult = dummyResponse.body()?.results
        assertEquals(expectedResult, actualResult)
        assertEquals(expectedResult!!.size, actualResult!!.size)
    }

    @Test
    fun `test getListArticles Network Error should return error`() = runBlockingTest {
        `when`(articleRepository.getListArticles()).thenAnswer {
            throw NetworkErrorException("Network Error")
        }
        // observe Snackbar LiveData
        val snackbarObserver = Observer<Boolean> {}
        articleViewModel.showNoInternetSnackbar.observeForever(snackbarObserver)
        articleViewModel.getListArticles()
        advanceUntilIdle()

        assertEquals(
            true,
            articleViewModel.showNoInternetSnackbar.value
        ) //check if SnackBar is shown
        assertTrue(articleViewModel.articles.value.isNullOrEmpty()) //check if articles live data is null or empty
        articleViewModel.showNoInternetSnackbar.removeObserver(snackbarObserver) // cleanup
    }

    @Test
    fun `test searchArticlesByTitle updates articles LiveData`() = runTest {
        val query = "Indonesia"
        `when`(articleRepository.searchArticlesByTitle(query)).thenReturn(dummyResponse)
        articleViewModel.searchArticlesByTitle(query)
        advanceUntilIdle()
        val actualResult = articleViewModel.articles.value
        val expectedResult = dummyResponse.body()?.results
        assertEquals(expectedResult, actualResult)
        assertEquals(expectedResult!!.size, actualResult!!.size)
    }


    @Test
    fun `test searchArticlesByTitle Network Error should return error`() = runBlockingTest {
        val query = "Indonesia"
        `when`(articleRepository.searchArticlesByTitle(query)).thenAnswer {
            throw NetworkErrorException("Network Error")
        }
        // observe Snackbar LiveData
        val snackbarObserver = Observer<Boolean> {}
        articleViewModel.showNoInternetSnackbar.observeForever(snackbarObserver)
        articleViewModel.searchArticlesByTitle(query)
        advanceUntilIdle()

        assertEquals(
            true,
            articleViewModel.showNoInternetSnackbar.value
        ) //check if SnackBar is shown
        assertTrue(articleViewModel.articles.value.isNullOrEmpty()) //check if articles live data is null or empty
        articleViewModel.showNoInternetSnackbar.removeObserver(snackbarObserver) // cleanup
    }

    @Test
    fun `test searchArticlesByTitle with random query returns empty list`() = runBlockingTest {
        val randomQuery = "randomquery123"
        `when`(articleRepository.searchArticlesByTitle(randomQuery)).thenReturn(
            Response.success(
                ApiResponse(count = 0, next = null, previous = null, results = emptyList())
            )
        )
        articleViewModel.searchArticlesByTitle(randomQuery)
        advanceUntilIdle()
        assertTrue(articleViewModel.articles.value.isNullOrEmpty())
    }

    @Test
    fun `test deleteAllRecentSearches calls repository method`() {
        articleViewModel.deleteAllRecentSearches()
        verify(articleRepository).deleteAllRecentSearches()
    }

    @Test
    fun `test setSnackBarValue updates snackbar LiveData`() {
        articleViewModel.setSnackBarValue(true)
        assertEquals(true, articleViewModel.showNoInternetSnackbar.value)
        articleViewModel.setSnackBarValue(false)
        assertEquals(false, articleViewModel.showNoInternetSnackbar.value)
    }

    @Test
    fun `test filterArticles with All Categories`() {
        articleViewModel.setArticles(DummyData.dummyArticles)
        articleViewModel.filterArticles("All Categories")
        assertEquals(DummyData.dummyArticles, articleViewModel.filteredArticles.value)
    }

    @Test
    fun `test filterArticles with specific news site`() {
        articleViewModel.setArticles(DummyData.dummyArticles)
        articleViewModel.filterArticles("Site B")
        val expectedFilteredArticles =
            listOf(DummyData.dummyArticles[1])//contains Site B category only
        assertEquals(expectedFilteredArticles, articleViewModel.filteredArticles.value)
    }


}