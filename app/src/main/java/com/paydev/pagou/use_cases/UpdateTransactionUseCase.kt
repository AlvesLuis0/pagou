package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.services.DatabaseService
import java.util.Date

class UpdateTransactionUseCase (private val context: Context) {
    private val transactionDao = DatabaseService
        .getInstance(context)
        .transactionDao()

    fun execute (id: Long, personId: Long, description: String?, value: Float, expiredAt: Date?) {
        AddTransactionUseCase(context).execute(personId, value, description, expiredAt)

        //val transaction = Transaction(id, personId, value, description, expiredAt, Date(), true)
        //transaction.id = transactionDao.insert(transaction)
        transactionDao.inactivateTransaction(id)
    }
}