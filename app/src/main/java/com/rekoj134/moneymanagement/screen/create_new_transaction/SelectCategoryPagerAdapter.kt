package com.rekoj134.moneymanagement.screen.create_new_transaction

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SelectCategoryPagerAdapter(fragmentActivity: FragmentActivity, private val fragments: List<Fragment>) : FragmentStateAdapter(fragmentActivity) {
    @SuppressLint("NonConstantResourceId")
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    companion object {
        const val TAB_EXPENSE = 0
        const val TAB_INCOME = 1
    }
}