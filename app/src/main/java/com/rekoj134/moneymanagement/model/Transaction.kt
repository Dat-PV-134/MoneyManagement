package com.rekoj134.moneymanagement.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rekoj134.moneymanagement.constant.TYPE_EXPENSE

@Entity(tableName = "tbl_transaction")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaction_id")
    val id: Int,
    @ColumnInfo(name = "transaction_name")
    val name: String,
    @ColumnInfo(name = "transaction_value")
    val value: Double,
    @ColumnInfo(name = "transaction_icon")
    val icon: Int,
    @ColumnInfo(name = "transaction_icon_color")
    val iconColor: String,
    @ColumnInfo(name = "transaction_date_time")
    val dateTime: Long,
    @ColumnInfo(name = "transaction_note")
    val note: String,
    @ColumnInfo(name = "transaction_type")
    val type: Int = TYPE_EXPENSE
)