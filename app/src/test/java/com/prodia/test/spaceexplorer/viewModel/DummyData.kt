//package com.prodia.test.spaceexplorer.viewModel
//
//import com.prodia.test.spaceexplorer.model.api.ApiResponse
//import com.prodia.test.spaceexplorer.model.data.Article
//import retrofit2.Response
//
//object DummyData {
//    fun getDummyArticle(): Article {
//        return Article(
//            id = 1,
//            title = "Dummy Title",
//            url = "https://example.com/dummy",
//            image_url = "https://example.com/dummy.jpg",
//            news_site = "News Site",
//            summary = "Summary",
//            published_at = "2022-12-22",
//            updated_at = "2022-12-22",
//            featured = false,
//            launches = emptyList(),
//            events = emptyList()
//        )
//    }
//
//    val dummyArticles = listOf(
//        dummyArticle,
//        dummyArticle.copy(id = 2, title = "Dummy Title 2", news_site = "Site B")
//    )
//
//    fun getDummyArticles(count: Int): List<Article> {
//        return List(count) { getDummyArticle() }
//    }
//
//    fun getDummyApiResponse(): ApiResponse {
//        return ApiResponse(
//            count = 1,
//            next = null,
//            previous = null,
//            results = getDummyArticles(1)
//        )
//    }
//
//    fun getDummyResponse(): Response<ApiResponse> {
//        val apiResponse = getDummyApiResponse()
//        return Response.success(apiResponse)
//    }
//}

package com.prodia.test.spaceexplorer.viewModel

import com.prodia.test.spaceexplorer.model.api.ApiResponse
import com.prodia.test.spaceexplorer.model.data.Article
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

