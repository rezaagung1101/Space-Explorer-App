package com.prodia.test.spaceexplorer.viewModel

import androidx.lifecycle.ViewModel
import com.prodia.test.spaceexplorer.model.repository.ArticleRepository
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

    @Test(expected = IllegalArgumentException::class)
    fun `create should throw IllegalArgumentException when modelClass is not ArticleViewModel`() {
        val invalidModelClass = ViewModel::class.java // Not ArticleViewModel
        factory.create(invalidModelClass)
    }

}