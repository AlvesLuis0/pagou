package com.paydev.pagou.use_cases
import android.content.Context
import com.paydev.pagou.models.Person
import com.paydev.pagou.services.DatabaseService

class UpdatePersonUseCase(context: Context) {
  private var personDao = DatabaseService.getInstance(context).personDao()

  fun execute(id: Long, name: String, contact: String, others: String?): Person {
    val person = Person(id, name, contact, others)
    person.validate()
    if (person.errors.isEmpty()) {
      personDao.update(person)
    }
    return person
  }
}