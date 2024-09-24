package com.paydev.pagou.daos

import androidx.room.Dao
import androidx.room.Query
import com.paydev.pagou.models.PersonBill
import com.paydev.pagou.models.PersonReport

@Dao
interface PersonReportDao {
    @Query("SELECT id,name,total FROM personreport WHERE id = :personId ")
    fun getByPersonId(personId: Long): PersonBill

    @Query("SELECT * FROM PersonReport")
    fun  getAll(): List<PersonReport>
}