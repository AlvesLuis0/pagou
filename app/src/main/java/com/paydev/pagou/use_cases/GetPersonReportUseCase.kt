package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.models.PersonBill
import com.paydev.pagou.models.TransactionSummary
import com.paydev.pagou.services.DatabaseService

class GetPersonReportUseCase (context: Context) {
    private val personReportDao = DatabaseService
        .getInstance(context)
        .personReportDao()
    private val transactionDao = DatabaseService
        .getInstance(context)
        .transactionDao()
    data class PersonReport(
        val bill: PersonBill,
        val transactions: List<TransactionSummary>
    )

    fun execute(id: Long): PersonReport {
        val bill = personReportDao.getByPersonId(id)
        val transactions = transactionDao.getByPersonId(id)
        return PersonReport(bill, transactions)
    }

}