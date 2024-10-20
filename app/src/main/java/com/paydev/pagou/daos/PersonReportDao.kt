package com.paydev.pagou.daos

import androidx.room.Dao
import androidx.room.Query
import com.paydev.pagou.models.Currency
import com.paydev.pagou.models.PersonReport

@Dao
interface PersonReportDao {
    @Query("SELECT * FROM PersonReport WHERE id = :personId")
    fun getByPersonId(personId: Long): PersonReport

    @Query("SELECT * FROM PersonReport ORDER BY name")
    fun getAll(): List<PersonReport>

    @Query("SELECT CASE WHEN :isPositive THEN TOTAL(CASE WHEN total > 0 THEN total ELSE 0 END) WHEN :isPositive = 0 THEN -TOTAL(CASE WHEN total < 0 THEN total ELSE 0 END) ELSE 0 END AS result FROM PersonReport")
    fun getTotal(isPositive: Boolean): Currency
}