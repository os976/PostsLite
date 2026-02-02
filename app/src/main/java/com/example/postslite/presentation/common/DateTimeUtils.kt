package com.example.postslite.presentation.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object DateTimeUtils {
    fun nowFormatted(): String {
        val sdf = java.text.SimpleDateFormat("EEE, dd MMM yyyy • hh:mm a", java.util.Locale.getDefault())
        return sdf.format(java.util.Date())
    }
}
