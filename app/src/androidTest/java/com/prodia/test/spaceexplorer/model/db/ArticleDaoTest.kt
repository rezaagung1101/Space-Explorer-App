package com.prodia.test.spaceexplorer.model.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.prodia.test.spaceexplorer.utils.DummyData
import com.prodia.test.spaceexplorer.utils.getOrAwaitValue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArticleDaoTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ArticleDatabase
    private lateinit var dao: ArticleDao
    private val sampleRecentSearch = DummyData.recentSearch

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ArticleDatabase::class.java
        ).build()
        dao = database.articleDao()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertRecentSearch_Success() = runTest{
        dao.insertRecentSearch(sampleRecentSearch)
        val actualRecentSearch = dao.getRecentSearchList().getOrAwaitValue()
        assertEquals(sampleRecentSearch.query, actualRecentSearch[0].query)
    }

    @Test
    fun deleteAllRecentSearches_Success() = runTest {
        dao.insertRecentSearch(sampleRecentSearch)
        dao.deleteAllRecentSearches()
        val actualRecentSearch = dao.getRecentSearchList().getOrAwaitValue()
        assertTrue(actualRecentSearch.isEmpty())
        assertFalse(actualRecentSearch.isNotEmpty())
    }

}