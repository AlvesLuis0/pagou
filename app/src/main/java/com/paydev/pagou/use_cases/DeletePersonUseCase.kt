package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.daos.PersonDao
import com.paydev.pagou.services.DatabaseService

class DeletePersonUseCase (context: Context) {
    private val personDao: PersonDao = DatabaseService
        .getInstance(context)
        .personDao()

    fun execute(id: Long){
        personDao.deleteById(id)
    }
}