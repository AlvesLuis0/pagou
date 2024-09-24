package com.paydev.pagou.daos

import androidx.room.Dao
import androidx.room.Query
import com.paydev.pagou.models.PersonBill
import com.paydev.pagou.models.PersonReport

@Dao
interface PersonReportDao {
    @Query("SELECT id,name,total FROM personreport WHERE id = :personId")
    fun getByPersonId(personId: Long): PersonBill

    @Query("SELECT * FROM PersonReport")
    fun getAll(): List<PersonReport>

    @Query("SELECT CASE WHEN :isPositive = 1 THEN TOTAL(CASE WHEN total > 0 THEN total ELSE 0 END) WHEN :isPositive = 0 THEN -TOTAL(CASE WHEN total < 0 THEN total ELSE 0 END) ELSE 0  END AS result FROM PersonReport;")
    fun getTotal(isPositive: Boolean): Float
}