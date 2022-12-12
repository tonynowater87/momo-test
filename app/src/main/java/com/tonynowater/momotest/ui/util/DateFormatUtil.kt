package com.tonynowater.momotest.ui.util

import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

object DateFormatUtil {

    private val sourceDateFormatter = DateTimeFormatterBuilder()
        .parseCaseInsensitive()
        .append(DateTimeFormatter.ISO_LOCAL_DATE)
        .appendLiteral(" ")
        .append(DateTimeFormatter.ISO_LOCAL_TIME)
        .toFormatter()

    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")

    fun formatLastUpdateDate(sourceDateString: String): String {
        return try {
            val date = sourceDateFormatter.parse(sourceDateString)
            dateFormatter.format(date)
        } catch (error: Exception) {
            ""
        }
    }
}