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
      errors.add("Nome é obrigatório")
    }
    if (contact.isBlank()){
      errors.add("Número de contato é obrigatório")
    }
    if(name.length < 3){
      errors.add("Nome precisa ter no mínimo 3 caracteres")
    }
    if(contact.length < 8){
      errors.add("Número de contato inválido")
    }
  }

}

