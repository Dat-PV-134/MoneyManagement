package com.rekoj134.moneymanagement.util

import com.rekoj134.moneymanagement.R
import com.rekoj134.moneymanagement.constant.ICON_CATEGORY_1
import com.rekoj134.moneymanagement.constant.ICON_CATEGORY_2
import com.rekoj134.moneymanagement.constant.ICON_CATEGORY_3
import com.rekoj134.moneymanagement.constant.ICON_CATEGORY_4
import com.rekoj134.moneymanagement.constant.ICON_CATEGORY_5
import com.rekoj134.moneymanagement.constant.ICON_CATEGORY_6
import com.rekoj134.moneymanagement.constant.TYPE_EXPENSE
import com.rekoj134.moneymanagement.constant.TYPE_INCOME
import com.rekoj134.moneymanagement.model.Category

object CategoryUtil {
    private val mapCategoryId by lazy { HashMap<Int, Int>() }

    init {
        mapCategoryId[ICON_CATEGORY_1] = R.drawable.ic_category_1
        mapCategoryId[ICON_CATEGORY_2] = R.drawable.ic_category_2
        mapCategoryId[ICON_CATEGORY_3] = R.drawable.ic_category_3

        mapCategoryId[ICON_CATEGORY_4] = R.drawable.ic_category_4
        mapCategoryId[ICON_CATEGORY_5] = R.drawable.ic_category_5
        mapCategoryId[ICON_CATEGORY_6] = R.drawable.ic_category_6
    }

    fun getCategoryId(iconKey: Int) : Int? {
        return mapCategoryId[iconKey]
    }

    fun getDefaultCategoryFirstTime() : List<Category> {
        return listOf(
            Category(0, "Clothing", ICON_CATEGORY_1, "#E86868", TYPE_EXPENSE),
            Category(0, "Car", ICON_CATEGORY_2, "#77C48A", TYPE_EXPENSE),
            Category(0, "Food", ICON_CATEGORY_3, "#6BCEE5", TYPE_EXPENSE),

            Category(0, "Salary", ICON_CATEGORY_4, "#6CDB70", TYPE_INCOME),
            Category(0, "Dividends", ICON_CATEGORY_5, "#75B7F1", TYPE_INCOME),
            Category(0, "Others", ICON_CATEGORY_6, "#F57979", TYPE_INCOME)
        )
    }
}