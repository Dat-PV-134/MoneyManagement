package com.rekoj134.moneymanagement.screen.create_new_transaction

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rekoj134.moneymanagement.R
import com.rekoj134.moneymanagement.databinding.FragmentSelectCategoryInDialogBinding
import com.rekoj134.moneymanagement.model.Category

class SelectCategoryInDialogFragment : Fragment {
    private var _binding: FragmentSelectCategoryInDialogBinding? = null
    private val binding get() = _binding

    private var isExpense: Boolean = true

    private lateinit var categoryAdapter: CategoryInDialogAdapter

    constructor(): super()

    // Not convert to main constructor because it make crash when configure changed (When configure changed, must have no parameter constructor for auto recreate fragment)
    constructor(isExpense: Boolean) : super() {
        this.isExpense = isExpense
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectCategoryInDialogBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        initData()
        handleEvent()
    }

    private fun handleEvent() {

    }

    private fun initData() {
        categoryAdapter = CategoryInDialogAdapter(requireContext())
        categoryAdapter.setListCategory(listOf(
            Category(0, "Clothes 1", R.drawable.ic_car, Color.parseColor("#F6F6F6")),
            Category(1, "Clothes 2", R.drawable.ic_category, Color.parseColor("#F8F6F6")),
            Category(2, "Clothes 3", R.drawable.ic_tshirt, Color.parseColor("#F1F6F6")),
            Category(3, "Clothes 4", R.drawable.ic_eat, Color.parseColor("#F5F6F6")),
        ))
        binding?.rvCategory?.adapter = categoryAdapter
    }

    private fun setupView() {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}