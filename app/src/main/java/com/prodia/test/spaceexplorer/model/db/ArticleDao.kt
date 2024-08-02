package com.prodia.test.spaceexplorer.model.db

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prodia.test.spaceexplorer.model.data.RecentSearch

interface ArticleDao {
    @Query("SELECT * FROM recent_search")
    fun getRecentSearchList(): LiveData<List<RecentSearch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recentSearch: RecentSearch)
}