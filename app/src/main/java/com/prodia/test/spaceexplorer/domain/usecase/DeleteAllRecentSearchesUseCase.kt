package com.prodia.test.spaceexplorer.domain.usecase

import com.prodia.test.spaceexplorer.domain.repository.ArticleRepository

class DeleteAllRecentSearchesUseCase(private val repository: ArticleRepository) {
    fun execute() {
        repository.deleteAllRecentSearches()
    }
}