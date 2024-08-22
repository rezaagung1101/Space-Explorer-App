package com.prodia.test.spaceexplorer.domain.usecase.recentSearch

import com.prodia.test.spaceexplorer.domain.repository.ArticleRepository

class DeleteAllRecentSearchesUseCase(private val repository: ArticleRepository) {
    fun execute() {
        repository.deleteAllRecentSearches()
    }
}