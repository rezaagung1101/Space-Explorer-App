package com.spaceexplorer.domain.usecase.articles

import com.spaceexplorer.domain.model.ApiResponse
import com.spaceexplorer.domain.repository.ArticleRepository
import retrofit2.Response

class GetListArticlesUseCase(private val repository: ArticleRepository) {
    suspend fun execute(): Response<ApiResponse> = repository.getListArticles()
}
