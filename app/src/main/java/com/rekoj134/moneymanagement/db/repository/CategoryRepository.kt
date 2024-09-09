package com.rekoj134.moneymanagement.db.repository

import com.rekoj134.moneymanagement.db.dao.CategoryDao
import com.rekoj134.moneymanagement.model.Category
import kotlinx.coroutines.flow.first

class CategoryRepository(private val dao: CategoryDao) {
    val categories = dao.getAllCategory()

    suspend fun getCategoryByType(type: Int) : List<Category> {
        return dao.getCategoryByType(type).first()
    }

    suspend fun insert(category: Category) : Long {
        return dao.insertCategory(category)
    }

    suspend fun insertAll(categories: List<Category>) {
        return dao.insertCategories(categories)
    }

    suspend fun update(category: Category) : Int {
        return dao.updateCategory(category)
    }

    suspend fun delete(category: Category) : Int {
        return dao.deleteCategory(category)
    }

    suspend fun deleteAll(): Int {
        return dao.deleteAllCategory()
    }
}