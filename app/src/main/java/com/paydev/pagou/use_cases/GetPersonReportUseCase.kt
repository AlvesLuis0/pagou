package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.models.PersonReport
import com.paydev.pagou.models.Transaction
import com.paydev.pagou.services.DatabaseService

class GetPersonReportUseCase (context: Context) {
    private val personReportDao = DatabaseService
        .getInstance(context)
        .personReportDao()
    private val transactionDao = DatabaseService
        .getInstance(context)
        .transactionDao()
    data class PersonReportInfo(
        val info: PersonReport,
        val transactions: List<Transaction>
    )

    fun execute(id: Long): PersonReportInfo {
        val info = personReportDao.getByPersonId(id)
        val transactions = transactionDao.getByPersonId(id)
        return PersonReportInfo(info, transactions)
    }

}