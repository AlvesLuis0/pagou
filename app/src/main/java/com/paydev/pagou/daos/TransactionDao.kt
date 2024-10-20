package com.paydev.pagou.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.paydev.pagou.models.Currency
import com.paydev.pagou.models.Transaction

@Dao
interface TransactionDao {
    @Insert
    fun insert (transaction: Transaction): Long

    @Query("SELECT * FROM `Transaction` WHERE personId = :personId ORDER BY registeredAt DESC")
    fun getByPersonId(personId: Long): List<Transaction>

    @Query("DELETE FROM `Transaction` WHERE personId = :personId")
    fun deleteAllByPersonId(personId: Long)

    @Query("UPDATE `Transaction` SET isActive = 0 WHERE id = :id")
    fun inactivate(id: Long)

    @Query("SELECT SUM(value) FROM `transaction` WHERE isActive = 1")
    fun getTotal(): Currency

    @Query("SELECT * FROM `Transaction` WHERE id = :id")
    fun getById(id: Long): Transaction
}