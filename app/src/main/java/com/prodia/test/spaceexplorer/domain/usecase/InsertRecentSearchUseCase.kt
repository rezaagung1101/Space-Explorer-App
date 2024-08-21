package com.prodia.test.spaceexplorer.domain.usecase

import com.prodia.test.spaceexplorer.domain.repository.ArticleRepository

class InsertRecentSearchUseCase(private val repository: ArticleRepository) {
    fun execute(query: String) {
        repository.insertRecentSearch(query)
    }
}