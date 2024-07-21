package com.rekoj134.moneymanagement.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rekoj134.moneymanagement.R
import com.rekoj134.moneymanagement.constant.ICON_CATEGORY_CAR
import com.rekoj134.moneymanagement.constant.ICON_CATEGORY_CLOTHE
import com.rekoj134.moneymanagement.constant.ICON_CATEGORY_EAT
import com.rekoj134.moneymanagement.constant.TYPE_EXPENSE
import com.rekoj134.moneymanagement.constant.TYPE_INCOME
import com.rekoj134.moneymanagement.databinding.ActivityMainBinding
import com.rekoj134.moneymanagement.model.Transaction
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listTransaction by lazy { ArrayList<Transaction>() }
    private lateinit var transactionAdapter: HomeTransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        setupView()
        handleEvent()
    }

    private fun handleEvent() {

    }

    private fun initData() {
        val time1 = System.currentTimeMillis()
        listTransaction.add(Transaction(0, "Clothing", 3000000.0, ICON_CATEGORY_CLOTHE, "#33AA7F", time1, "", TYPE_EXPENSE))
        listTransaction.add(Transaction(1, "Eating", 4000000.0, ICON_CATEGORY_EAT, "#F5CF47", time1, "", TYPE_EXPENSE))
        listTransaction.add(Transaction(2, "Car", 5000000.0, ICON_CATEGORY_CAR, "#F34D4D", time1, "", TYPE_EXPENSE))
        listTransaction.add(Transaction(3, "Car", 2000000.0, ICON_CATEGORY_CAR, "#F34D4D", time1, "", TYPE_INCOME))
        listTransaction.add(Transaction(4, "Car", 5000000.0, ICON_CATEGORY_CAR, "#F34D4D", time1, "", TYPE_EXPENSE))
        listTransaction.add(Transaction(5, "Car", 6000000.0, ICON_CATEGORY_CAR, "#F34D4D", time1, "", TYPE_INCOME))

        val time2 = System.currentTimeMillis() - 96400000
        listTransaction.add(Transaction(6, "Eating", 6000000.0, ICON_CATEGORY_EAT, "#F5CF47", time2, "", TYPE_EXPENSE))
        listTransaction.add(Transaction(7, "Eating", 7000000.0, ICON_CATEGORY_EAT, "#F5CF47", time2, "", TYPE_EXPENSE))

        val time3 = System.currentTimeMillis() - 196400000
        listTransaction.add(Transaction(8, "Eating", 8000000.0, ICON_CATEGORY_EAT, "#F5CF47", time3, "", TYPE_EXPENSE))

        val time4 = System.currentTimeMillis() - 296400000
        listTransaction.add(Transaction(9, "Car", 9000000.0, ICON_CATEGORY_CAR, "#F34D4D", time4, "", TYPE_EXPENSE))
        listTransaction.add(Transaction(10, "Car", 6000000.0, ICON_CATEGORY_CAR, "#F34D4D", time1, "", TYPE_INCOME))

        transactionAdapter = HomeTransactionAdapter(this@MainActivity)
    }

    private fun setupView() {
        binding.tvTitleTop.text = getTopTitle()

        binding.rvTransaction.adapter = transactionAdapter

        listTransaction.sortWith(compareByDescending {
            it.dateTime
        })
        transactionAdapter.setListTransaction(listTransaction)
        transactionAdapter.notifyDataSetChanged()
    }

    private fun getTopTitle() : String {
        val curDate: Date = Calendar.getInstance().time
        val dateFormatter= SimpleDateFormat("yyyy-MM", Locale.getDefault())
        val formattedDate = dateFormatter.format(curDate)
        return formattedDate + " " + getString(R.string.balance)
    }
}