package com.prodia.test.spaceexplorer.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Helper {

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatPublishedAt(publishedAt: String): String {
        val localDateTime = LocalDateTime.parse(publishedAt, DateTimeFormatter.ISO_DATE_TIME)
        return localDateTime.format(DateTimeFormatter.ofPattern("d MMMM yyyy, HH:mm"))
    }

    fun extractSummary(summary: String): String {
        return summary.substringBefore(".")
    }
}