package com.rekoj134.moneymanagement.util

import com.rekoj134.moneymanagement.R
import com.rekoj134.moneymanagement.constant.ICON_CATEGORY_CAR
import com.rekoj134.moneymanagement.constant.ICON_CATEGORY_CLOTHE
import com.rekoj134.moneymanagement.constant.ICON_CATEGORY_EAT

object CategoryUtil {
    private val mapCategoryId by lazy { HashMap<Int, Int>() }

    init {
        mapCategoryId[ICON_CATEGORY_CLOTHE] = R.drawable.ic_tshirt
        mapCategoryId[ICON_CATEGORY_CAR] = R.drawable.ic_car
        mapCategoryId[ICON_CATEGORY_EAT] = R.drawable.ic_eat
    }

    fun getCategoryId(iconKey: Int) : Int? {
        return mapCategoryId[iconKey]
    }
}