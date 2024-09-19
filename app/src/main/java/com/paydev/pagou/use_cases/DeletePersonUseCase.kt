package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.daos.PersonDao
import com.paydev.pagou.services.ServiceLocator

class DeletePersonUseCase (context: Context) {
    private val personDao: PersonDao = ServiceLocator
        .databaseService(context)
        .personDao()

    fun execute(id: Long){
        val person = personDao.getById(id)
        personDao.delete(person)
    }
}