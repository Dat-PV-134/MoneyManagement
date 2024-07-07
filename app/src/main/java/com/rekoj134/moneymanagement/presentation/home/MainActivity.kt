package com.rekoj134.moneymanagement.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rekoj134.moneymanagement.R
import com.rekoj134.moneymanagement.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        initData()
        handleEvent()
    }

    private fun handleEvent() {

    }

    private fun initData() {

    }

    private fun setupView() {
        binding.tvTitleTop.text = getTopTitle()
    }

    private fun getTopTitle() : String {
        val curDate: Date = Calendar.getInstance().time
        val dateFormatter= SimpleDateFormat("yyyy-MM", Locale.getDefault())
        val formattedDate = dateFormatter.format(curDate)
        return formattedDate + " " + getString(R.string.balance)
    }
}