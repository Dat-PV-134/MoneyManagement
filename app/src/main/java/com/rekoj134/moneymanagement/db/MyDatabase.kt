package com.rekoj134.moneymanagement.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rekoj134.moneymanagement.db.dao.CategoryDao
import com.rekoj134.moneymanagement.db.dao.TransactionDao
import com.rekoj134.moneymanagement.model.Category
import com.rekoj134.moneymanagement.model.Transaction
import kotlin.concurrent.Volatile

@Database(entities = [Transaction::class, Category::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    "my_database")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}