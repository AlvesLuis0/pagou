package com.paydev.pagou.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.paydev.pagou.models.Person

@Dao
interface PersonDao {
  @Insert
  fun insert(person: Person): Long

  @Update
  fun update(person: Person): Int

  @Query("DELETE FROM person WHERE id = :id")
  fun deleteById(id: Long)
}