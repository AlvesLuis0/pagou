package com.paydev.pagou.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.paydev.pagou.models.Transaction
import com.paydev.pagou.models.TransactionSummary

@Dao
interface TransactionDao {
    @Insert
    fun insert (transaction: Transaction): Long

    @Update
    fun update (transaction: Transaction): Int

    @Delete
    fun delete (transaction: Transaction): Int

    @Query("Select id,value,description,classification,expiration,registration,status FROM `Transaction` WHERE personId = :personId ORDER BY registration DESC")
    fun getByPersonId(personId: Long): List<TransactionSummary>

    @Query("Delete FROM `Transaction` WHERE personId = :personId")
    fun deleteAllByPersonId(personId: Long)
}