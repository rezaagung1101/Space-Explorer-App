package com.prodia.test.spaceexplorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prodia.test.spaceexplorer.domain.repository.ArticleRepository

class ArticleViewModelFactory(private val repository: ArticleRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}