package com.paydev.pagou.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.paydev.pagou.models.Transaction

@Dao
interface TransactionDao {
    @Insert
    fun insert (transaction: Transaction): Long

    @Update
    fun update (transaction: Transaction): Int

    @Delete
    fun delete (transaction: Transaction): Int
}