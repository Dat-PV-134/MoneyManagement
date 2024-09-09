package com.rekoj134.moneymanagement.app

import android.app.Application
import com.rekoj134.moneymanagement.db.MyDatabase
import com.rekoj134.moneymanagement.db.repository.CategoryRepository
import com.rekoj134.moneymanagement.prefercence.MyPreference
import com.rekoj134.moneymanagement.util.CategoryUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MyPreference.init(this@MyApplication)

        // Insert default categories when users use app first time
        if (MyPreference.read(MyPreference.PREF_FIRST_SETUP_DATABASE, true)) {
            val categoryDao = MyDatabase.getInstance(this@MyApplication).categoryDao()
            val categoryRepository = CategoryRepository(categoryDao)
            CoroutineScope(Dispatchers.IO).launch {
                categoryRepository.insertAll(CategoryUtil.getDefaultCategoryFirstTime())
            }
            MyPreference.write(MyPreference.PREF_FIRST_SETUP_DATABASE, false)
        }
    }
}