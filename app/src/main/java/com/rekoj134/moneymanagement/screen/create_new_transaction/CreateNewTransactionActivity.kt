package com.rekoj134.moneymanagement.screen.create_new_transaction

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.rekoj134.moneymanagement.base.BaseActivity
import com.rekoj134.moneymanagement.databinding.ActivityCreateNewTransactionBinding
import com.rekoj134.moneymanagement.databinding.BottomSheetSelectCategoryBinding
import com.rekoj134.moneymanagement.model.Category
import com.rekoj134.moneymanagement.util.CategoryUtil

class CreateNewTransactionActivity : BaseActivity() {
    private lateinit var binding: ActivityCreateNewTransactionBinding
    private var bottomSheetDialogSelectCategory: BottomSheetDialog? = null

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
        bottomSheetDialogSelectCategory = BottomSheetDialog(this@CreateNewTransactionActivity)
        bottomSheetDialogSelectCategory?.setContentView(dialogBinding.root)
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

        if (bottomSheetDialogSelectCategory?.isShowing == false) {
            bottomSheetDialogSelectCategory?.show()
        }
    }

    fun updateCurCategory(category: Category) {
        CategoryUtil.getCategoryId(category.icon)?.let {
            binding.imgCategory.setImageResource(it)
        }
        binding.imgCategory.backgroundTintList = ColorStateList.valueOf(Color.parseColor(category.iconColor))

        if (bottomSheetDialogSelectCategory?.isShowing == true) {
            bottomSheetDialogSelectCategory?.dismiss()
        }
    }

    private fun setupView() {
        showDialogSelectCategory()
    }
}