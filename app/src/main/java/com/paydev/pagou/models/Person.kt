package com.paydev.pagou.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(ignoredColumns = ["errors"])
data class Person(
  @PrimaryKey(autoGenerate = true)
  var id: Long,
  var name: String,
  var contact: String,
  var others: String?
) : CustomEntity() {
  fun validate(){
    errors.clear()

    if(name.isBlank()){
      errors.add("nome não pode ser nulo")
    }
    if (contact.isBlank()){
      errors.add("número de contato não pode ser nulo")
    }
    if(name.length !in 3..16){
      errors.add("O nome precisa ter entre 3 e 16 caracteres ")
    }
    if(contact.length !in 8..9){
      errors.add("O número de contato inválido")
    }
  }

}

