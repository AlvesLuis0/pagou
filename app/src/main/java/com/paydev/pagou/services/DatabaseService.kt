package com.paydev.pagou.services

import android.content.Context
import androidx.room.BuiltInTypeConverters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.paydev.pagou.converters.DateConverter
import com.paydev.pagou.daos.PersonDao
import com.paydev.pagou.daos.PersonReportDao
import com.paydev.pagou.daos.TransactionDao
import com.paydev.pagou.models.Person
import com.paydev.pagou.models.Transaction

@Database(entities = [Person::class, Transaction::class], version = 1)
@TypeConverters(DateConverter::class, builtInTypeConverters = BuiltInTypeConverters())
abstract class DatabaseService : RoomDatabase() {
  abstract fun personDao(): PersonDao
  abstract fun transactionDao(): TransactionDao
  abstract fun PersonReportDao(): PersonReportDao

  companion object {
    fun create(context: Context): DatabaseService {
      return Room
        .databaseBuilder(context, DatabaseService::class.java, "pagou.db")
        .allowMainThreadQueries()
        .build()
    }
  }
}