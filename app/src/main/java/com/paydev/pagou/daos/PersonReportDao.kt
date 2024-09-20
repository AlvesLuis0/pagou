package com.paydev.pagou.daos

import androidx.room.Dao
import androidx.room.Query
import com.paydev.pagou.models.PersonBill

@Dao
interface PersonReportDao {
    @Query("SELECT p.name, SUM(t.value) AS total FROM person p JOIN `transaction` t ON p.id = t.personId AND p.id = :personId")
    fun getByPersonId(personId: Long): PersonBill
}