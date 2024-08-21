package com.prodia.test.spaceexplorer.data.source.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prodia.test.spaceexplorer.domain.model.RecentSearch

@Dao
interface ArticleDao {
    @Query("SELECT * FROM recent_search")
    fun getRecentSearchList(): LiveData<List<RecentSearch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecentSearch(recentSearch: RecentSearch)

    @Query("DELETE FROM recent_search")
    fun deleteAllRecentSearches()
}