package com.paydev.pagou.converters

import androidx.room.TypeConverter
import java.util.Date


class DateConverter {
    @TypeConverter
    fun longToDate(long: Long?): Date? {
        return long?.let { Date(it) }
    }

    @TypeConverter
    fun dateToLong(date: Date?): Long? {
        return date?.time
    }
}