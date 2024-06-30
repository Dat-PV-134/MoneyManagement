package com.rekoj134.moneymanagement.presentation.home

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.rekoj134.moneymanagement.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ImageView>(R.id.btnCategory).setOnClickListener {

        }

        findViewById<ImageView>(R.id.btnChart).setOnClickListener {

        }


        findViewById<ImageView>(R.id.btnAdd).setOnClickListener {

        }
    }
}