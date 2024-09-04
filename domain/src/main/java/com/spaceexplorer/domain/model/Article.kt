package com.spaceexplorer.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val id: Int,
    val title: String,
    val url: String,
    val image_url: String,
    val news_site: String,
    val summary: String,
    val published_at: String,
    val updated_at: String,
    val featured: Boolean,
    val launches: List<Launch>,
    val events: List<Event>
): Parcelable

@Parcelize
data class Launch(
    val launch_id: String,
    val provider: String
): Parcelable

@Parcelize
data class Event(
    val event_id: String,
    val provider: String
): Parcelable


@Entity(tableName = "recent_search")
data class RecentSearch(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val query: String,
//    val category: String
)

