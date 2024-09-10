package com.paydev.pagou.services

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.paydev.pagou.daos.PersonDao
import com.paydev.pagou.models.Person

@Database(entities = [Person::class], version = 1)
abstract class DatabaseService : RoomDatabase() {
  abstract fun personDao(): PersonDao

  companion object {
    fun create(context: Context): DatabaseService {
      return Room
        .databaseBuilder(context, DatabaseService::class.java, "pagou.db")
        .allowMainThreadQueries()
        .build()
    }
  }
}