package com.prodia.test.spaceexplorer.model.api

import com.prodia.test.spaceexplorer.model.data.Article

data class ApiResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Article>
)