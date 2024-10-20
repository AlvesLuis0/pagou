package com.paydev.pagou.services

import android.content.Context
import androidx.room.BuiltInTypeConverters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.paydev.pagou.converters.CurrencyConverter
import com.paydev.pagou.converters.DateConverter
import com.paydev.pagou.daos.PersonDao
import com.paydev.pagou.daos.PersonReportDao
import com.paydev.pagou.daos.TransactionDao
import com.paydev.pagou.models.Person
import com.paydev.pagou.models.PersonReport
import com.paydev.pagou.models.Transaction

@Database(entities = [Person::class, Transaction::class], views = [PersonReport::class] , version = 1)
@TypeConverters(DateConverter::class, CurrencyConverter::class, builtInTypeConverters = BuiltInTypeConverters())
abstract class DatabaseService : RoomDatabase() {
  abstract fun personDao(): PersonDao
  abstract fun transactionDao(): TransactionDao
  abstract fun personReportDao(): PersonReportDao

  companion object {
    private var instance: DatabaseService? = null

    fun getInstance(context: Context): DatabaseService {
      if(instance == null) {
        instance = Room
          // .inMemoryDatabaseBuilder(context, DatabaseService::class.java)
          .databaseBuilder(context, DatabaseService::class.java, "pagou.db")
          .allowMainThreadQueries()
          .build()
      }
      return instance!!
    }
  }
}