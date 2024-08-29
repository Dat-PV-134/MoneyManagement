package com.rekoj134.moneymanagement.presentation.create_new_transaction

import android.os.Bundle
import com.rekoj134.moneymanagement.base.BaseActivity
import com.rekoj134.moneymanagement.databinding.ActivityCreateNewTransactionBinding

class CreateNewTransactionActivity : BaseActivity() {
    private lateinit var binding: ActivityCreateNewTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        handleEvent()
    }

    private fun handleEvent() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnDate.setOnClickListener {

        }

        binding.btnTime.setOnClickListener {

        }

        binding.btnNote.setOnClickListener {

        }
    }

    private fun setupView() {

    }
}