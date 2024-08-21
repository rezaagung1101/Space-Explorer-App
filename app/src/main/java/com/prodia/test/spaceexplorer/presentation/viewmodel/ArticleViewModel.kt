package com.prodia.test.spaceexplorer.presentation.viewmodel

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prodia.test.spaceexplorer.domain.model.Article
import com.prodia.test.spaceexplorer.domain.model.RecentSearch
import com.prodia.test.spaceexplorer.domain.repository.ArticleRepository
import com.prodia.test.spaceexplorer.domain.usecase.DeleteAllRecentSearchesUseCase
import com.prodia.test.spaceexplorer.domain.usecase.GetListArticlesUseCase
import com.prodia.test.spaceexplorer.domain.usecase.GetRecentSearchesUseCase
import com.prodia.test.spaceexplorer.domain.usecase.InsertRecentSearchUseCase
import com.prodia.test.spaceexplorer.domain.usecase.SearchArticlesByTitleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val getListArticlesUseCase: GetListArticlesUseCase,
    private val searchArticlesByTitleUseCase: SearchArticlesByTitleUseCase,
    private val insertRecentSearchUseCase: InsertRecentSearchUseCase,
    private val deleteAllRecentSearchesUseCase: DeleteAllRecentSearchesUseCase,
    private val getRecentSearchesUseCase: GetRecentSearchesUseCase
) : ViewModel() {
    private val _filteredArticles = MutableLiveData<List<Article>>()
    val filteredArticles: LiveData<List<Article>> = _filteredArticles
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles
    private val _newsSites = MutableLiveData<List<String>>()
    val newsSites: LiveData<List<String>> = _newsSites
    private val _recentSearches = getRecentSearchesUseCase.execute()
    val recentSearches: LiveData<List<RecentSearch>> = _recentSearches
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _showNoInternetSnackbar = MutableLiveData<Boolean>()
    val showNoInternetSnackbar: LiveData<Boolean>
        get() = _showNoInternetSnackbar

    //added for test only
    fun setArticles(articles: List<Article>) {
        _articles.value = articles
    }

    fun getListArticles() =
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = getListArticlesUseCase.execute()
                if (response.isSuccessful) {
                    val articles = response.body()!!.results
                    setArticles(articles)
                    _newsSites.value = articles.map { it.news_site }.distinct()
                } else {
//                    Log.d("TAG", "GET Article Error Code: ${response.code()}")
                }
                _isLoading.value = false
            } catch (e: NetworkErrorException) {
//                Log.e("TAG", "Network error: ${e.message}")
                setSnackBarValue(true)
                _isLoading.value = false
            }
        }

    fun searchArticlesByTitle(title: String) =
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = searchArticlesByTitleUseCase.execute(title)
                if (response.isSuccessful) {
                    val articles = response.body()!!.results
                    setArticles(articles)
                    _newsSites.value = articles.map { it.news_site }.distinct()
                    insertRecentSearchUseCase.execute(title)
                } else {
//                    Log.d("TAG", "GET Article Error Code: ${response.code()}")
                }
                _isLoading.value = false
            } catch (e: NetworkErrorException) {
//                Log.e("TAG", "Network error: ${e.message}")
                setSnackBarValue(true)
                _isLoading.value = false
            }
        }

    fun filterArticles(newsSite: String) {
        if (newsSite == "All Categories") {
            _filteredArticles.value = _articles.value
        } else {
            _filteredArticles.value = _articles.value?.filter { it.news_site == newsSite }
        }
    }

    fun deleteAllRecentSearches() = deleteAllRecentSearchesUseCase.execute()

    fun setSnackBarValue(status: Boolean) {
        _showNoInternetSnackbar.value = status
    }
}
