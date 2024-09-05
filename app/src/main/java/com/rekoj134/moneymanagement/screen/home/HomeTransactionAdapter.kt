package com.rekoj134.moneymanagement.screen.home

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
import com.rekoj134.moneymanagement.util.ThemeUtil
import java.util.Date

class HomeTransactionAdapter(private val context: Context) : RecyclerView.Adapter<HomeTransactionViewHolder>() {
    private val listTransaction by lazy { ArrayList<Transaction>() }
    private var oldDate: Date? = null

    fun setListTransaction(listTransaction: List<Transaction>) {
        this.listTransaction.clear()
        this.listTransaction.addAll(listTransaction)
    }

    // Notify item range change with payload
    fun refreshRecyclerView() {
        notifyItemRangeChanged(0, listTransaction.size, "change_theme")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTransactionBinding.inflate(inflater)
        return HomeTransactionViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HomeTransactionViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        // if payload == change theme -> not rebind item and only change theme
        if (payloads.any { it == "change_theme" }) {
            holder.bindChangeTheme(context)
        } else super.onBindViewHolder(holder, position, payloads)
    }

    override fun onBindViewHolder(holder: HomeTransactionViewHolder, position: Int) {
        val item = listTransaction[position]
        Log.e("ANCUTKO", oldDate.toString() + " - " + DateTimeUtil.convertMillisToDate(item.dateTime).toString())
        if (oldDate == null || oldDate != DateTimeUtil.convertMillisToDate(item.dateTime)) {
            oldDate = DateTimeUtil.convertMillisToDate(item.dateTime)
            var curPos = position
            var canStop = false
            var totalExpense = 0.0
            var totalIncome = 0.0
            while (curPos < listTransaction.size && !canStop) {
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

    // method to change theme of each view again
    fun bindChangeTheme(context: Context) {
        Log.e("ANCUTKO", "COME HERE")
        binding.tvDate.setTextColor(ThemeUtil.getResColor(context, R.attr.text_color_1))
        binding.tvTotal.setTextColor(ThemeUtil.getResColor(context, R.attr.text_color_1))
        binding.tvTransactionName.setTextColor(ThemeUtil.getResColor(context, R.attr.text_color_1))
        binding.tvMoney.setTextColor(ThemeUtil.getResColor(context, R.attr.text_color_1))
        binding.containerAll.setBackgroundColor(ThemeUtil.getResColor(context, R.attr.background_color_3))
        binding.layoutItem.setBackgroundColor(ThemeUtil.getResColor(context, R.attr.background_color_1))
    }
}
