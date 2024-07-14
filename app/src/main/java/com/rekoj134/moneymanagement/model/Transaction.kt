package com.rekoj134.moneymanagement.model

import com.rekoj134.moneymanagement.constant.TYPE_EXPENSE

data class Transaction(
    val id: Int,
    val name: String,
    val value: Double,
    val icon: Int,
    val iconColor: String,
    val dateTime: Long,
    val note: String,
    val type: Int = TYPE_EXPENSE
)