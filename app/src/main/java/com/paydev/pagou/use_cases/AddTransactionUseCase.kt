package com.paydev.pagou.use_cases
import android.content.Context
import com.paydev.pagou.models.Transaction
import com.paydev.pagou.services.DatabaseService
import java.util.Date

class AddTransactionUseCase(context: Context) {
    private val transactionDao = DatabaseService
        .getInstance(context)
        .transactionDao()

    fun execute (personId: Long , value: Float, description:String?): Transaction {
        val transaction = Transaction (0, personId, value, description, Date(), true)
        transaction.id = transactionDao.insert(transaction)
        return transaction
    }
}