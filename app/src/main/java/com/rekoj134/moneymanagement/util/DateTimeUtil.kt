package com.rekoj134.moneymanagement.util

import com.rekoj134.moneymanagement.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale
import java.util.TimeZone

object DateTimeUtil {
    fun convertMillisToDate(timeInMillis: Long) : Date {
        return Date(timeInMillis)
    }

    fun convertMillisToDateStr(timeInMillis: Long) : String {
        val curDate: Date = Date(timeInMillis)
        val dateFormatter= SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val formattedDate = dateFormatter.format(curDate)
        return formattedDate
    }
}