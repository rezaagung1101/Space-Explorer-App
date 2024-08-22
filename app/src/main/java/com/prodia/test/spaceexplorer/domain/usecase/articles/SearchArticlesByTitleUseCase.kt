package com.prodia.test.spaceexplorer.domain.usecase.articles

import com.prodia.test.spaceexplorer.data.source.remote.api.ApiResponse
import com.prodia.test.spaceexplorer.domain.repository.ArticleRepository
import retrofit2.Response

class SearchArticlesByTitleUseCase(private val repository: ArticleRepository){
    suspend fun execute(title: String): Response<ApiResponse> {
        return repository.searchArticlesByTitle(title)
    }
}