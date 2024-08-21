package com.prodia.test.spaceexplorer.utils

import com.prodia.test.spaceexplorer.data.source.remote.api.ApiResponse
import com.prodia.test.spaceexplorer.domain.model.Article
import retrofit2.Response

object DummyData {
    private val dummyArticle = Article(
        id = 1,
        title = "Dummy Title",
        url = "https://example.com/dummy",
        image_url = "https://example.com/dummy.jpg",
        news_site = "News Site",
        summary = "Summary",
        published_at = "2022-12-22",
        updated_at = "2022-12-22",
        featured = false,
        launches = emptyList(),
        events = emptyList()
    )

    val dummyArticles = listOf(
        dummyArticle,
        dummyArticle.copy(id = 2, title = "Dummy Title 2", news_site = "Site B")
    )

    fun getDummyApiResponse(): ApiResponse {
        return ApiResponse(
            count = dummyArticles.size,
            next = null,
            previous = null,
            results = dummyArticles
        )
    }

    fun getDummyResponse(): Response<ApiResponse> {
        val apiResponse = getDummyApiResponse()
        return Response.success(apiResponse)
    }
}

