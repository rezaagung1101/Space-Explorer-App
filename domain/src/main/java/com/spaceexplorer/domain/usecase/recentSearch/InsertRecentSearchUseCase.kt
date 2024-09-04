package com.spaceexplorer.domain.usecase.recentSearch

import com.spaceexplorer.domain.repository.ArticleRepository

class InsertRecentSearchUseCase(private val repository: ArticleRepository) {
    fun execute(query: String) {
        repository.insertRecentSearch(query)
    }
}