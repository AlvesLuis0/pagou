package com.paydev.pagou.daos

import androidx.room.Dao
import androidx.room.Query
import com.paydev.pagou.models.PersonBill

@Dao
interface PersonReportDao {
    @Query("SELECT p.id, p.name, SUM(t.value) AS total FROM person p LEFT JOIN `transaction` t ON p.id = t.personId WHERE p.id = :personId AND t.isActive = 1 GROUP BY p.id")
    fun getByPersonId(personId: Long): PersonBill
}