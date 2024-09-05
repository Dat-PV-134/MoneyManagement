package com.rekoj134.moneymanagement.screen.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.rekoj134.moneymanagement.screen.home.MainActivity
import com.rekoj134.moneymanagement.R

class SplashActivity : AppCompatActivity() {
//    private lateinit var handler: Handler
//    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        handler = Handler(Looper.getMainLooper())
//
//        // Runnable khai bao nhung logic muon xu ly
//        runnable = object : Runnable {
//            override fun run() {
//                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//            }
//        }
//
//        // Handler dung de cho khoang thoi gian sau day trien khai logic code trong thang runnable
//        handler.postDelayed(runnable, 4000)

        Handler(Looper.getMainLooper()).postDelayed({
            // logic code ben trong thang runnable
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 4000)
    }
}