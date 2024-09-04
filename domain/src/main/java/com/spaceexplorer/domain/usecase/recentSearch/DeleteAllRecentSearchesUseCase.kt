package com.spaceexplorer.domain.usecase.recentSearch

import com.spaceexplorer.domain.repository.ArticleRepository

class DeleteAllRecentSearchesUseCase(private val repository: ArticleRepository) {
    fun execute() {
        repository.deleteAllRecentSearches()
    }
}