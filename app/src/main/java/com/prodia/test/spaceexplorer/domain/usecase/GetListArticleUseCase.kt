package com.prodia.test.spaceexplorer.domain.usecase

import com.prodia.test.spaceexplorer.data.source.remote.api.ApiResponse
import com.prodia.test.spaceexplorer.domain.repository.ArticleRepository
import retrofit2.Response

class GetListArticlesUseCase(private val repository: ArticleRepository){
    suspend fun execute(): Response<ApiResponse> {
        return repository.getListArticles()
    }
}
