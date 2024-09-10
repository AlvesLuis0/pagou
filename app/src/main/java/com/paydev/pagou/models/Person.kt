package com.paydev.pagou.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
  @PrimaryKey(autoGenerate = true)
  var id: Long?,
  var name: String,
  var contact: String,
  var others: String?
)
