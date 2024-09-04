package com.spaceexplorer.domain.usecase.recentSearch

import androidx.lifecycle.LiveData
import com.spaceexplorer.domain.model.RecentSearch
import com.spaceexplorer.domain.repository.ArticleRepository

class GetRecentSearchesUseCase(private val repository: ArticleRepository){
    fun execute(): LiveData<List<RecentSearch>> {
        return repository.getRecentSearches()
    }
}
