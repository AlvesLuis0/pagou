package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.daos.TransactionDao
import com.paydev.pagou.services.DatabaseService
import java.util.Date

class UpdateTransactionUseCase (context: Context) {
    private val transactionDao: TransactionDao = DatabaseService
        .getInstance(context)
        .transactionDao()
    private val _context = context

    fun execute (id: Long, personId: Long, description: String?, value: Float, expiredAt: Date?) {
        AddTransactionUseCase(_context).execute(personId, value, description, expiredAt)

        //val transaction = Transaction(id, personId, value, description, expiredAt, Date(), true)
        //transaction.id = transactionDao.insert(transaction)
        transactionDao.inactivateTransaction(id)
    }
}