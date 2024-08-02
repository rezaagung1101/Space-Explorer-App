package com.prodia.test.spaceexplorer.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prodia.test.spaceexplorer.model.data.Article
import com.prodia.test.spaceexplorer.model.data.RecentSearch
import com.prodia.test.spaceexplorer.model.repository.ArticleRepository
import kotlinx.coroutines.launch

class ArticleViewModel(private val repository: ArticleRepository) : ViewModel() {
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles
    private val _newsSites = MutableLiveData<List<String>>()
    val newsSites: LiveData<List<String>> = _newsSites
//    private val _recentSearches = MutableLiveData<List<String>>()
//    val recentSearches: LiveData<List<String>> = _recentSearches
    private val _recentSearches = repository.getRecentSearches()
    val recentSearches: LiveData<List<RecentSearch>> = _recentSearches

    fun getListArticles() {
        viewModelScope.launch {
            _articles.value = repository.getListArticles()
        }
    }

    fun searchArticlesByTitle(title: String) {
        viewModelScope.launch {
            _articles.value = repository.searchArticlesByTitle(title)
            repository.insertRecentSearch(title)
        }
    }

    fun filterArticles(newsSite: String) {
        viewModelScope.launch {
            _articles.value = repository.filterArticles(newsSite)
        }
    }

    fun getNewsSites() {
        viewModelScope.launch {
            _newsSites.value = repository.getNewsSites()
        }
    }

}