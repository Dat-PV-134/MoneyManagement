package com.rekoj134.moneymanagement.presentation.home

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rekoj134.moneymanagement.databinding.ItemTransactionBinding
import com.rekoj134.moneymanagement.model.Transaction
import com.rekoj134.moneymanagement.util.CategoryUtil

class HomeTransactionAdapter(private val context: Context) : RecyclerView.Adapter<HomeTransactionViewHolder>() {
    private val listTransaction by lazy { ArrayList<Transaction>() }

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
        holder.bind(context, listTransaction[position], position)
    }

    override fun getItemCount(): Int {
        return listTransaction.size
    }
}

class HomeTransactionViewHolder(private val binding: ItemTransactionBinding) : ViewHolder(binding.root) {
    fun bind(context: Context, transaction: Transaction, position: Int) {
        binding.tvTransactionName.text = transaction.name
        binding.tvMoney.text = "-" + transaction.value

        CategoryUtil.getCategoryId(transaction.icon)?.let {
            binding.imgCategory.setImageResource(it)
        }
        binding.imgCategory.backgroundTintList = ColorStateList.valueOf(Color.parseColor(transaction.iconColor))
    }
}
