package com.spaceexplorer.domain.usecase.articles

import com.spaceexplorer.domain.model.ApiResponse
import com.spaceexplorer.domain.repository.ArticleRepository
import retrofit2.Response

class SearchArticlesByTitleUseCase(private val repository: ArticleRepository){
    suspend fun execute(title: String): Response<ApiResponse> {
        return repository.searchArticlesByTitle(title)
    }
}