package com.rekoj134.moneymanagement.presentation.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rekoj134.moneymanagement.R
import com.rekoj134.moneymanagement.constant.TYPE_EXPENSE
import com.rekoj134.moneymanagement.databinding.ItemTransactionBinding
import com.rekoj134.moneymanagement.model.Transaction
import com.rekoj134.moneymanagement.util.CategoryUtil
import com.rekoj134.moneymanagement.util.CurrencyConverter
import com.rekoj134.moneymanagement.util.DateTimeUtil
import java.util.Date

class HomeTransactionAdapter(private val context: Context) : RecyclerView.Adapter<HomeTransactionViewHolder>() {
    private val listTransaction by lazy { ArrayList<Transaction>() }
    private var oldDate: Date? = null

    fun setListTransaction(listTransaction: List<Transaction>) {
        this.listTransaction.clear()
        this.listTransaction.addAll(listTransaction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTransactionBinding.inflate(inflater)
        return HomeTransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeTransactionViewHolder, position: Int) {
        val item = listTransaction[position]
        if (oldDate == null || oldDate != DateTimeUtil.convertMillisToDate(item.dateTime)) {
            oldDate = DateTimeUtil.convertMillisToDate(item.dateTime)
            var curPos = position
            var canStop = false
            var totalExpense = 0.0
            var totalIncome = 0.0
            while (curPos < listTransaction.size && !canStop) {
                Log.e("ANCUTKO", curPos.toString() + " - " + listTransaction.size)
                if (oldDate != DateTimeUtil.convertMillisToDate(listTransaction[curPos].dateTime)) {
                    canStop = true
                } else {
                    if (listTransaction[curPos].type == TYPE_EXPENSE) totalExpense += listTransaction[curPos].value
                    else totalIncome += listTransaction[curPos].value
                }
                curPos++
            }
            holder.bind(context, item, position, true, totalExpense, totalIncome)
        } else {
            oldDate = DateTimeUtil.convertMillisToDate(item.dateTime)
            holder.bind(context, item, position, false, -1.0, -1.0)
        }
    }

    override fun getItemCount(): Int {
        return listTransaction.size
    }
}

class HomeTransactionViewHolder(private val binding: ItemTransactionBinding) : ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(context: Context, transaction: Transaction, position: Int, isNewDate: Boolean, totalExpense: Double, totalIncome: Double) {
        if (!isNewDate) {
            binding.layoutTop.visibility = View.GONE
            binding.viewMargin.visibility = View.GONE
        } else {
            binding.tvTotal.isSelected = true
        }

        binding.tvTotal.text = context.getString(R.string.expense) + "-" + CurrencyConverter.convertToCurrency(totalExpense) +
                if (totalIncome != 0.0) ", " + context.getString(R.string.income) + "+" + CurrencyConverter.convertToCurrency(totalIncome)
                else ""

        binding.tvDate.text = DateTimeUtil.convertMillisToDateStr(transaction.dateTime)
        binding.tvTransactionName.text = transaction.name
        binding.tvMoney.text = if (transaction.type == TYPE_EXPENSE) "-" + CurrencyConverter.convertToCurrency(transaction.value)
                else "+" + CurrencyConverter.convertToCurrency(transaction.value)

        CategoryUtil.getCategoryId(transaction.icon)?.let {
            binding.imgCategory.setImageResource(it)
        }
        binding.imgCategory.backgroundTintList = ColorStateList.valueOf(Color.parseColor(transaction.iconColor))
    }
}
