package com.rekoj134.moneymanagement.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rekoj134.moneymanagement.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM tbl_category ORDER BY category_id ASC")
    fun getAllCategory(): Flow<List<Category>>

    @Query("SELECT * FROM tbl_category WHERE category_type == :type ORDER BY category_id ASC")
    fun getCategoryByType(type: Int): Flow<List<Category>>

    @Insert
    suspend fun insertCategory(category: Category) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<Category>)

    @Update
    suspend fun updateCategory(category: Category) : Int

    @Delete
    suspend fun deleteCategory(category: Category) : Int

    @Query("DELETE FROM tbl_category")
    suspend fun deleteAllCategory() : Int
}