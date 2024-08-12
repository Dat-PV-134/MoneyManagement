package com.rekoj134.moneymanagement.util

import android.content.Context
import com.rekoj134.moneymanagement.R
import com.rekoj134.moneymanagement.constant.DARK_THEME
import com.rekoj134.moneymanagement.constant.LIGHT_THEME
import com.rekoj134.moneymanagement.prefercence.MyPreference

object ThemeUtil {
    fun getTheme(context: Context): Int {
        val curTheme: Int = MyPreference.read(MyPreference.PREF_THEME, R.style.Theme_LightTheme)
        when (curTheme) {
            DARK_THEME -> return R.style.Theme_DarkTheme
            LIGHT_THEME -> return R.style.Theme_LightTheme
        }
        return R.style.Theme_LightTheme
    }

    fun getResColor(context: Context, attr: Int): Int {
        var intColor = 0
        try {
            val themeId = getTheme(context)
            val a = context.theme.obtainStyledAttributes(themeId, intArrayOf(attr))
            intColor = a.getColor(0, 0)
            a.recycle()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return intColor
    }

    fun getHexColor(context: Context, attr: Int): String {
        var hexColor = "#FFFFFF"
        try {
            val themeId = getTheme(context)
            val a = context.theme.obtainStyledAttributes(themeId, intArrayOf(attr))
            hexColor = Integer.toHexString(a.getColor(0, 0))
            a.recycle()
            return hexColor
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return hexColor
    }
}