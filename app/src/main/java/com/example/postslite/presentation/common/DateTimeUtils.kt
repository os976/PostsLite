package com.example.postslite.presentation.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTimeUtils {

    fun timeOnly(): String {
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return sdf.format(Date())
    }

    fun nowFormatted(): String {
        val sdf = SimpleDateFormat("EEE, dd MMM yyyy • hh:mm a", Locale.getDefault())
        return sdf.format(Date())
    }
}
