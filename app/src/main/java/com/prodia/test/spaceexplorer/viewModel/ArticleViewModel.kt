package com.prodia.test.spaceexplorer.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prodia.test.spaceexplorer.model.data.Article
import com.prodia.test.spaceexplorer.model.data.RecentSearch
import com.prodia.test.spaceexplorer.model.repository.ArticleRepository
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class ArticleViewModel(private val repository: ArticleRepository) : ViewModel() {
    private val _filteredArticles = MutableLiveData<List<Article>>()
    val filteredArticles: LiveData<List<Article>> = _filteredArticles
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles
    private val _newsSites = MutableLiveData<List<String>>()
    val newsSites: LiveData<List<String>> = _newsSites
    private val _recentSearches = repository.getRecentSearches()
    val recentSearches: LiveData<List<RecentSearch>> = _recentSearches
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _showNoInternetSnackbar = MutableLiveData<Boolean>()
    val showNoInternetSnackbar: LiveData<Boolean>
        get() = _showNoInternetSnackbar

    fun getListArticles() =
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = repository.getListArticles()
                if (response.isSuccessful) {
                    val articles = response.body()!!.results
                    _articles.value = articles
                    _newsSites.value =  articles.map { it.news_site }.distinct()
                } else {
                    Log.d("TAG", "GET Article Error Code: ${response.code()}")
                }
                _isLoading.value = false
            } catch (e: UnknownHostException) {
                Log.e("TAG", "Network error: ${e.message}")
                setSnackBarValue(true)
                _isLoading.value = false
            }
        }

    fun searchArticlesByTitle(title: String) =
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = repository.searchArticlesByTitle(title)
                if (response.isSuccessful) {
                    val articles = response.body()!!.results
                    _articles.value = articles
                    _newsSites.value =  articles.map { it.news_site }.distinct()
                    repository.insertRecentSearch(title)
                } else {
                    Log.d("TAG", "GET Article Error Code: ${response.code()}")
                }
                _isLoading.value = false
            } catch (e: UnknownHostException) {
                Log.e("TAG", "Network error: ${e.message}")
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

    fun deleteAllRecentSearches() = repository.deleteAllRecentSearches()


    fun setSnackBarValue(status: Boolean) {
        _showNoInternetSnackbar.value = status
    }
}
