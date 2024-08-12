package com.rekoj134.moneymanagement.app

import android.app.Application
import com.rekoj134.moneymanagement.prefercence.MyPreference

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MyPreference.init(this@MyApplication)
    }
}