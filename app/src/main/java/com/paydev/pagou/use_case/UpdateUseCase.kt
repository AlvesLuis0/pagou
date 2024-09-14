package com.paydev.pagou.use_case
import android.content.Context
import  com.paydev.pagou.daos.PersonDao
import com.paydev.pagou.models.Person
import com.paydev.pagou.services.ServiceLocator

class UpdateUseCase(context: Context){
    private var personDao: PersonDao = ServiceLocator.databaseService(context).personDao()
    fun execute(id: Long, name: String, contact: String, others: String?): Boolean {
        val person = Person(id, name, contact, others)
        val success = personDao.update(person) > 0
        return success
    }
}