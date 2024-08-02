package com.prodia.test.spaceexplorer.model.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val id: Int,
    val title: String,
    val summary: String,
    val published_at: String,
    val imageUrl: String,
    val news_site: String
): Parcelable

@Entity(tableName = "recent_search")
data class RecentSearch(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val query: String
)
