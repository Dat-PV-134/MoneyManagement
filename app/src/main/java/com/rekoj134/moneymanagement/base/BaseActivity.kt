package com.rekoj134.moneymanagement.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rekoj134.moneymanagement.R
import com.rekoj134.moneymanagement.util.ThemeUtil

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(ThemeUtil.getTheme(this@BaseActivity))
    }
}