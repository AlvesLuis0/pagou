package com.paydev.pagou.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.paydev.pagou.models.Person

@Dao
interface PersonDao {
  @Insert
  fun insert(person: Person): Long

  @Update
  fun update(person: Person): Int

  @Delete
  fun delete(person: Person): Int
}