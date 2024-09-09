package com.rekoj134.moneymanagement.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rekoj134.moneymanagement.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM tbl_transaction ORDER BY transaction_id ASC")
    fun getAllTransaction(): Flow<List<Transaction>>

    @Insert
    suspend fun insertTransaction(transaction: Transaction) : Long

    @Update
    suspend fun updateTransaction(transaction: Transaction) : Int

    @Delete
    suspend fun deleteTransaction(transaction: Transaction) : Int

    @Query("DELETE FROM tbl_transaction")
    suspend fun deleteAllTransaction() : Int
}