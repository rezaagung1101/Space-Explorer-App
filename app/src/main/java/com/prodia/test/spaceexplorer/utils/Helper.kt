package com.prodia.test.spaceexplorer.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.prodia.test.spaceexplorer.R
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone

object Helper {

    fun formatPublishedAt(publishedAt: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val localDateTime = LocalDateTime.parse(publishedAt, DateTimeFormatter.ISO_DATE_TIME)
            localDateTime.format(DateTimeFormatter.ofPattern("d MMMM yyyy, HH:mm"))
        } else {
            val isoFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            isoFormatter.timeZone = TimeZone.getTimeZone("UTC")
            val date = isoFormatter.parse(publishedAt)
            val outputFormatter = SimpleDateFormat("d MMMM yyyy, HH:mm", Locale.getDefault())
            outputFormatter.format(date)
        }
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