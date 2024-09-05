package com.rekoj134.moneymanagement.model

import com.rekoj134.moneymanagement.constant.TYPE_EXPENSE

data class Category(
    val id: Int,
    val name: String,
    val icon: Int,
    val iconColor: String,
    val type: Int = TYPE_EXPENSE
)