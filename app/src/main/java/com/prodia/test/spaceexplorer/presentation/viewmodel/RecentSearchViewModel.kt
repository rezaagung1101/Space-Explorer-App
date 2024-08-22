package com.prodia.test.spaceexplorer.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prodia.test.spaceexplorer.domain.model.RecentSearch
import com.prodia.test.spaceexplorer.domain.usecase.recentSearch.DeleteAllRecentSearchesUseCase
import com.prodia.test.spaceexplorer.domain.usecase.recentSearch.GetRecentSearchesUseCase
import com.prodia.test.spaceexplorer.domain.usecase.recentSearch.InsertRecentSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentSearchViewModel @Inject constructor(
    private val insertRecentSearchUseCase: InsertRecentSearchUseCase,
    private val deleteAllRecentSearchesUseCase: DeleteAllRecentSearchesUseCase,
    private val getRecentSearchesUseCase: GetRecentSearchesUseCase
): ViewModel() {

    private val _recentSearches = getRecentSearchesUseCase.execute()
    val recentSearches: LiveData<List<RecentSearch>> = _recentSearches


    fun insertRecentSearch(title: String) = viewModelScope.launch {
        insertRecentSearchUseCase.execute(title)
    }

    fun deleteAllRecentSearches() = deleteAllRecentSearchesUseCase.execute()

}