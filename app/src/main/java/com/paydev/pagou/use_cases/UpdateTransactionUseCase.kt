package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.models.Transaction
import com.paydev.pagou.services.DatabaseService

class UpdateTransactionUseCase (private val context: Context) {
    private val transactionDao = DatabaseService
        .getInstance(context)
        .transactionDao()

    fun execute (id: Long, personId: Long, description: String?, value: Double): Transaction {
        val transaction = AddTransactionUseCase(context)
            .execute(personId, value, description)
        if (transaction.errors.isEmpty()) {
            transactionDao.inactivate(id)
        }

        return transaction
    }
}