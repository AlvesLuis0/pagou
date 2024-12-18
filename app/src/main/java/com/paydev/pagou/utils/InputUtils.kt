package com.paydev.pagou.utils

import android.widget.EditText

object InputUtils {
  fun getString(input: EditText): String {
    return input.text.toString()
  }

  fun getStringOrNull(input: EditText): String? {
    return getString(input).ifBlank { null }
  }

  fun getDoubleOrNull(input: EditText): Double? {
    return getStringOrNull(input)?.toDoubleOrNull()
  }
}