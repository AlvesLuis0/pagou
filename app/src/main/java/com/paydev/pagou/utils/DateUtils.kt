package com.paydev.pagou.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
  fun format(date: Date): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(date)
  }
}