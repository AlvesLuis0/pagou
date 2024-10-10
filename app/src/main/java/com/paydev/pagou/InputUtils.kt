package com.paydev.pagou

import android.widget.EditText

object InputUtils {
  fun getStringOrNull(input: EditText): String? {
    return input.text.toString().ifBlank { null }
  }

  fun getFloatOrNull(input: EditText): Float? {
    return getStringOrNull(input)?.toFloatOrNull()
  }
}