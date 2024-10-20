package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.services.DatabaseService

class DeleteTransactionUseCase(context: Context) {
    private val transactionDao = DatabaseService.getInstance(context).transactionDao()

    fun execute (id: Long){
        transactionDao.inactivate(id)
    }
}