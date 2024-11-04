package com.paydev.pagou.models

import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import java.util.Locale

class Currency {
  private var value: Long = 0

  constructor(value: Long) {
    setValue(value)
  }

  constructor(value: Double) {
    setValue(value)
  }

  fun setValue(value: Double) {
    this.value = (value * 100).toLong()
  }

  fun setValue(value: Long) {
    this.value = value
  }

  fun getValue(): Double {
    return value / 100.toDouble()
  }

  fun getRealValue(): Long {
    return value
  }

  fun isNegative(): Boolean {
    return value < 0
  }

  fun isPositive(): Boolean {
    return value > 0
  }

  fun isZero(): Boolean {
    return value == 0L
  }

  override fun toString(): String {
    val locale = Locale("en", "US")
    val formatter = NumberFormat.getInstance(locale) as DecimalFormat
    formatter.applyPattern("#.##")
    return formatter.format(getValue())
  }
}