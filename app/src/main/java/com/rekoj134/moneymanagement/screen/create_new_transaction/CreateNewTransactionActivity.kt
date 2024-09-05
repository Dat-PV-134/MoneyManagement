package com.rekoj134.moneymanagement.screen.create_new_transaction

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.rekoj134.moneymanagement.base.BaseActivity
import com.rekoj134.moneymanagement.databinding.ActivityCreateNewTransactionBinding
import com.rekoj134.moneymanagement.databinding.BottomSheetSelectCategoryBinding

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

        binding.imgCategory.setOnClickListener {
            showDialogSelectCategory()
        }
    }

    private fun showDialogSelectCategory() {
        val dialogBinding = BottomSheetSelectCategoryBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(this@CreateNewTransactionActivity)
        bottomSheetDialog.setContentView(dialogBinding.root)
        dialogBinding.viewPager.adapter = SelectCategoryPagerAdapter(this@CreateNewTransactionActivity,
            listOf(SelectCategoryInDialogFragment(), SelectCategoryInDialogFragment(false)))
        dialogBinding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                dialogBinding.tabLayout.getTabAt(position)?.select()
            }
        })

        dialogBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                dialogBinding.viewPager.currentItem = tab?.position ?: 0
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
            }
        })
        bottomSheetDialog.show()
    }

    private fun setupView() {
        showDialogSelectCategory()
    }
}