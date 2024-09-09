package com.rekoj134.moneymanagement.util

import com.rekoj134.moneymanagement.R
import com.rekoj134.moneymanagement.constant.ICON_CATEGORY_0
import com.rekoj134.moneymanagement.constant.ICON_CATEGORY_1
import com.rekoj134.moneymanagement.constant.ICON_CATEGORY_2
import com.rekoj134.moneymanagement.constant.TYPE_EXPENSE
import com.rekoj134.moneymanagement.model.Category

object CategoryUtil {
    private val mapCategoryId by lazy { HashMap<Int, Int>() }

    init {
        mapCategoryId[ICON_CATEGORY_0] = R.drawable.ic_category_1
        mapCategoryId[ICON_CATEGORY_1] = R.drawable.ic_category_2
        mapCategoryId[ICON_CATEGORY_2] = R.drawable.ic_category_3
    }

    fun getCategoryId(iconKey: Int) : Int? {
        return mapCategoryId[iconKey]
    }

    fun getDefaultCategoryFirstTime() : List<Category> {
        return listOf(
            Category(0, "Clothing", ICON_CATEGORY_0, "#E86868", TYPE_EXPENSE),
            Category(0, "Car", ICON_CATEGORY_1, "#77C48A", TYPE_EXPENSE),
            Category(0, "Food", ICON_CATEGORY_2, "#6BCEE5", TYPE_EXPENSE)
        )
    }
}