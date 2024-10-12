package com.paydev.pagou.converters

import androidx.room.TypeConverter
import com.paydev.pagou.models.Currency

class CurrencyConverter {
  @TypeConverter
  fun longToCurrency(long: Long?): Currency? {
    return long?.let { Currency(it) }
  }

  @TypeConverter
  fun currencyToLong(currency: Currency?): Long? {
    return currency?.getRealValue()
  }
}