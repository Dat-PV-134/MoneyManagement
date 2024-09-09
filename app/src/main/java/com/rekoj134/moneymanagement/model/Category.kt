package com.rekoj134.moneymanagement.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rekoj134.moneymanagement.constant.TYPE_EXPENSE

@Entity(tableName = "tbl_category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    val id: Int = 0,
    @ColumnInfo(name = "category_name")
    val name: String,
    @ColumnInfo(name = "category_icon")
    val icon: Int,
    @ColumnInfo(name = "category_icon_color")
    val iconColor: String,
    @ColumnInfo(name = "category_type")
    val type: Int = TYPE_EXPENSE
)