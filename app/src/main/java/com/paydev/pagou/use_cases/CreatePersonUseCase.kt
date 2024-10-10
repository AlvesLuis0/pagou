package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.models.Person
import com.paydev.pagou.services.DatabaseService

class CreatePersonUseCase (context: Context) {
  private val personDao = DatabaseService
    .getInstance(context)
    .personDao()

  fun execute(name: String, contact: String, others: String?): Person {
    val person = Person(0, name, contact, others)
    if (person.name.isBlank()) {
      person.errors.add("O nome não pode ser nulo")
    }
    if (person.contact.isBlank()) {
      person.errors.add("O número de contato não pode ser nulo")
    }
    if(person.name.length in 2..17){
      person.errors.add("O nome precisa ter entre 3 e 16 caracteres ")
    }
    if (person.contact.length in 7..10) {
      person.errors.add("O número de contato inválido")
    }
    if (person.errors.isEmpty()) {
      person.id = personDao.insert(person)
    }
    return person
  }
}
