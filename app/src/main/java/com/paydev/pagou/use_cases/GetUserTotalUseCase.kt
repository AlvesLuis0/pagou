package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.services.DatabaseService

class GetUserTotalUseCase(context: Context) {
    private val personReportDao =  DatabaseService.getInstance(context).personReportDao()
    fun execute(onlyPositive:Boolean): Float {
        return personReportDao.getTotal(onlyPositive)
    }
}