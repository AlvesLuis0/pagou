package com.paydev.pagou.models

class Currency {
  private var value: Long = 0

  constructor(value: Long) {
    setValue(value)
  }

  constructor(value: Float) {
    setValue(value)
  }

  fun setValue(value: Float) {
    this.value = (value * 100).toLong()
  }

  fun setValue(value: Long) {
    this.value = value
  }

  fun getValue(): Float {
    return value / 100f
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

  override fun toString(): String {
    return "%.2f".format(getValue())
  }
}