package com.prodia.test.spaceexplorer.utils

import android.os.Build
import androidx.annotation.RequiresApi
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.format.DateTimeParseException

class HelperTest {

    @Test
    @RequiresApi(Build.VERSION_CODES.O)
    fun `test formatPublishedAt with valid date string`() {
        val publishedAt = "2024-08-15T10:30:00"
        val expectedFormat = "15 August 2024, 10:30"
        val result = Helper.formatPublishedAt(publishedAt)
        assertEquals(expectedFormat, result)
    }

    @Test(expected = DateTimeParseException::class)
    @RequiresApi(Build.VERSION_CODES.O)
    fun `test formatPublishedAt with invalid date string`() {
        val invalidPublishedAt = "2024-08-15:30:00"
        Helper.formatPublishedAt(invalidPublishedAt)
    }

    @Test
    fun `test extractSummary with valid summary`() {
        val summary = "This is a sample summary. It contains multiple sentences."
        val expectedResult = "This is a sample summary."
        val result = Helper.extractSummary(summary)
        assertEquals(expectedResult, result)
    }

}
