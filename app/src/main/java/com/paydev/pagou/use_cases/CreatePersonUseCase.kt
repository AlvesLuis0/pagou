package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.daos.PersonDao
import com.paydev.pagou.models.Person
import com.paydev.pagou.services.ServiceLocator

class CreatePersonUseCase (context: Context){
    private val personDao: PersonDao = ServiceLocator
        .databaseService(context)
        .personDao()


    fun execute (name: String, contact: String, others: String?): Person{
        val person = Person( 0, name, contact, others)
        person.id = personDao.insert(person)
        return person
    }
}