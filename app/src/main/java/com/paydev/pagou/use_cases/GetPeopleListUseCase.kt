package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.models.PersonReport
import com.paydev.pagou.services.DatabaseService

class GetPeopleListUseCase(context:Context) {
    private val personReportDao = DatabaseService.getInstance(context).personReportDao()
    fun execute(): List<PersonReport> {
        return personReportDao.getAll()
    }
}