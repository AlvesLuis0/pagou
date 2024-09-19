package com.paydev.pagou.use_cases
import android.content.Context
import com.paydev.pagou.daos.TransactionDao
import com.paydev.pagou.models.Transaction
import com.paydev.pagou.services.ServiceLocator
import java.util.Date

class AddTransactionUseCase(context: Context) {
    private val transactionDao: TransactionDao = ServiceLocator
        .databaseService(context)
        .transactionDao()

    fun execute (personId: Long , value: Float, description:String?, expiredAt: Date?): Transaction {
        val transaction = Transaction (0, personId, value, description, expiredAt, Date(System.currentTimeMillis()), true)
        transaction.id = transactionDao.insert(transaction)
        return transaction
    }
}