package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.services.DatabaseService

class OffsetTransactionUseCase(private val context: Context) {
    private val transactionDao = DatabaseService.getInstance(context).transactionDao()

    fun execute(id:Long){
        val transaction = transactionDao.getById(id)
        AddTransactionUseCase(context).execute(transaction.personId, -transaction.value, null, null)

    }
}