package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.services.DatabaseService

class ClearTransactionUseCase(context: Context) {
    private val _context = context
    private val transactionDao = DatabaseService.getInstance(context).transactionDao()

    fun execute(personId: Long){
        (_context)
        transactionDao.deleteAllByPersonId(personId)
    }
}