package com.paydev.pagou.daos

import androidx.room.Dao
import androidx.room.Query
import com.paydev.pagou.models.Currency
import com.paydev.pagou.models.PersonBill
import com.paydev.pagou.models.PersonReport

@Dao
interface PersonReportDao {
    @Query("SELECT id, name, contact, others, lastPayment, total FROM PersonReport WHERE id = :personId")
    fun getByPersonId(personId: Long): PersonBill

    @Query("SELECT * FROM PersonReport ORDER BY name")
    fun getAll(): List<PersonReport>

    @Query("SELECT CASE WHEN :isPositive = 1 THEN TOTAL(CASE WHEN total > 0 THEN total ELSE 0 END) WHEN :isPositive = 0 THEN -TOTAL(CASE WHEN total < 0 THEN total ELSE 0 END) ELSE 0 END AS result FROM PersonReport")
    fun getTotal(isPositive: Boolean): Currency
}