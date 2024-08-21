package com.prodia.test.spaceexplorer.domain.usecase

import androidx.lifecycle.LiveData
import com.prodia.test.spaceexplorer.domain.model.RecentSearch
import com.prodia.test.spaceexplorer.domain.repository.ArticleRepository

class GetRecentSearchesUseCase(private val repository: ArticleRepository){
    fun execute(): LiveData<List<RecentSearch>> {
        return repository.getRecentSearches()
    }
}
