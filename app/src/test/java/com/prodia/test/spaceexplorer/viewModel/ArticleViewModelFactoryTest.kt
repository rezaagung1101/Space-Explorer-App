package com.prodia.test.spaceexplorer.viewModel

import com.prodia.test.spaceexplorer.domain.repository.ArticleRepository
import com.prodia.test.spaceexplorer.presentation.viewmodel.ArticleViewModel
import com.prodia.test.spaceexplorer.presentation.viewmodel.ArticleViewModelFactory
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock

class ArticleViewModelFactoryTest {
    private val repository = mock(ArticleRepository::class.java)
    private val factory = ArticleViewModelFactory(repository)

    @Test
    fun `create should return ArticleViewModel when modelClass is ArticleViewModel`() {
        val modelClass = ArticleViewModel::class.java
        val viewModel = factory.create(modelClass)
        assertTrue(viewModel is ArticleViewModel)
    }

}