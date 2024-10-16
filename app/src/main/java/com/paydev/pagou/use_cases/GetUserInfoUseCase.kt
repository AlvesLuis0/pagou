package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.models.Currency
import com.paydev.pagou.models.PersonReport
import com.paydev.pagou.services.DatabaseService

class GetUserInfoUseCase(context: Context) {
    private val personReportDao =  DatabaseService.getInstance(context).personReportDao()

    data class UserInfo(
        val total: Currency,
        val people: List<PersonReport>
    )

    fun execute(onlyPositive:Boolean): UserInfo {
        val total = personReportDao.getTotal(onlyPositive)
        val people = personReportDao.getAll()
        return UserInfo(total, people)
    }
}