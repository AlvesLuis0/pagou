package com.paydev.pagou.use_cases
import android.content.Context
import  com.paydev.pagou.daos.PersonDao
import com.paydev.pagou.models.Person
import com.paydev.pagou.services.DatabaseService

class UpdatePersonUseCase(context: Context) {
  private var personDao: PersonDao = DatabaseService.getInstance(context).personDao()

  fun execute(id: Long, name: String, contact: String, others: String?): Person {
    val person = Person(id, name, contact, others)
    personDao.update(person)
    return person
  }
}