package com.prodia.test.spaceexplorer.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prodia.test.spaceexplorer.model.data.RecentSearch

@Dao
interface ArticleDao {
    @Query("SELECT * FROM recent_search")
    fun getRecentSearchList(): LiveData<List<RecentSearch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recentSearch: RecentSearch)

    @Query("DELETE FROM recent_search") // Ganti dengan nama tabel yang sesuai
    fun deleteAllRecentSearches()
}