package com.paydev.pagou.services

import android.content.Context

object ServiceLocator {
  private var databaseService: DatabaseService? = null
  fun databaseService(context: Context): DatabaseService {
    if(databaseService == null) databaseService = DatabaseService.create(context)
    return databaseService!!
  }
}