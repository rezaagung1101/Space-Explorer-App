package com.prodia.test.spaceexplorer.data.source.remote.api

import com.prodia.test.spaceexplorer.domain.model.Article

data class ApiResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Article>
)
