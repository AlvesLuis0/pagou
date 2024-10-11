package com.paydev.pagou

import android.widget.EditText

object InputUtils {
  fun getString(input: EditText): String {
    return input.text.toString()
  }

  fun getStringOrNull(input: EditText): String? {
    return getString(input).ifBlank { null }
  }

  fun getFloatOrNull(input: EditText): Float? {
    return getStringOrNull(input)?.toFloatOrNull()
  }
}