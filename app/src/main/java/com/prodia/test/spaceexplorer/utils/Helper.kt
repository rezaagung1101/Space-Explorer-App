package com.prodia.test.spaceexplorer.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.prodia.test.spaceexplorer.R
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Helper {

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatPublishedAt(publishedAt: String): String {
        val localDateTime = LocalDateTime.parse(publishedAt, DateTimeFormatter.ISO_DATE_TIME)
        return localDateTime.format(DateTimeFormatter.ofPattern("d MMMM yyyy, HH:mm"))
    }

    fun extractSummary(summary: String): String {
        return summary.substringBefore(". ") + "."
    }

    fun showDialog(
        context: Context,
        message: String,
        negativeMsg: String,
        positiveMsg: String,
        positiveListener: () -> Unit,
    ) {
        AlertDialog.Builder(context).apply {
            setMessage(message)
            setNegativeButton(negativeMsg, null)
            setPositiveButton(positiveMsg) { _, _ ->
                positiveListener.invoke()
            }
            val dialog = this.create()
            dialog.show()
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(context, R.color.danger))
            dialog.window?.setBackgroundDrawableResource(R.drawable.rounded_dialog)
        }
    }

}