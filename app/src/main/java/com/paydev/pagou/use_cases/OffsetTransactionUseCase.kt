package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.services.DatabaseService

class OffsetTransactionUseCase(context: Context) {
    private val _context = context
    private val transactionDao = DatabaseService.getInstance(context).transactionDao()

    fun execute(id:Long){
        val transaction = transactionDao.getById(id)
        AddTransactionUseCase(_context).execute(transaction.personId, -transaction.value, null, null)

    }
}