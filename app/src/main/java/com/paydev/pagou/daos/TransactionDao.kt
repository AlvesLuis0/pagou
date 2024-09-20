package com.paydev.pagou.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.paydev.pagou.models.Transaction
import com.paydev.pagou.models.TransactionSummary

@Dao
interface TransactionDao {
    @Insert
    fun insert (transaction: Transaction): Long

    @Query("SELECT id,value,description,expiredAt,registeredAt,isActive FROM `Transaction` WHERE personId = :personId ORDER BY registeredAt DESC")
    fun getByPersonId(personId: Long): List<TransactionSummary>

    @Query("DELETE FROM `Transaction` WHERE personId = :personId")
    fun deleteAllByPersonId(personId: Long)

    @Query("UPDATE `Transaction` SET isActive = 0 WHERE id = :id")
    fun inactivateTransaction(id: Long)
}