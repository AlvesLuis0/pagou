package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.models.Transaction
import com.paydev.pagou.services.DatabaseService
import java.util.Date

class UpdateTransactionUseCase (private val context: Context) {
    private val transactionDao = DatabaseService
        .getInstance(context)
        .transactionDao()

    fun execute (id: Long, personId: Long, description: String?, value: Float, expiredAt: Date?): Transaction {
        transactionDao.inactivateTransaction(id)
        val transaction = AddTransactionUseCase(context)
            .execute(personId, value, description)
        return transaction
    }
}