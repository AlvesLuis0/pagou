package com.paydev.pagou.use_cases
import android.content.Context
import com.paydev.pagou.models.Currency
import com.paydev.pagou.models.Transaction
import com.paydev.pagou.services.DatabaseService
import java.util.Date

class AddTransactionUseCase(context: Context) {
    private val transactionDao = DatabaseService
        .getInstance(context)
        .transactionDao()

    fun execute (personId: Long , value: Double, description:String?): Transaction {
        val transaction = Transaction (0, personId, Currency(value), description, Date(), true)
        transaction.validate()
        if (transaction.errors.isEmpty()) {
            transaction.id = transactionDao.insert(transaction)
        }
        return transaction
    }
}