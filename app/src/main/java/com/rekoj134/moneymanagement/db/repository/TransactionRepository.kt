package com.rekoj134.moneymanagement.db.repository

import com.rekoj134.moneymanagement.db.dao.TransactionDao
import com.rekoj134.moneymanagement.model.Transaction

class TransactionRepository(private val dao: TransactionDao) {
    val transactions = dao.getAllTransaction()

    suspend fun insert(transaction: Transaction) : Long {
        return dao.insertTransaction(transaction)
    }

    suspend fun update(transaction: Transaction) : Int {
        return dao.updateTransaction(transaction)
    }

    suspend fun delete(transaction: Transaction) : Int {
        return dao.deleteTransaction(transaction)
    }

    suspend fun deleteAll() : Int {
        return dao.deleteAllTransaction()
    }
}