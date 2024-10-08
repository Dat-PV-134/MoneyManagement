package com.rekoj134.moneymanagement.screen.create_new_transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rekoj134.moneymanagement.constant.TYPE_EXPENSE
import com.rekoj134.moneymanagement.constant.TYPE_INCOME
import com.rekoj134.moneymanagement.databinding.FragmentSelectCategoryInDialogBinding
import com.rekoj134.moneymanagement.db.MyDatabase
import com.rekoj134.moneymanagement.db.repository.CategoryRepository
import com.rekoj134.moneymanagement.model.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SelectCategoryInDialogFragment : Fragment {
    private var _binding: FragmentSelectCategoryInDialogBinding? = null
    private val binding get() = _binding

    private var isExpense: Boolean = true

    private lateinit var categoryAdapter: CategoryInDialogAdapter
    private val listCategory by lazy { ArrayList<Category>() }

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
        categoryAdapter = CategoryInDialogAdapter(requireContext()) {
            (requireActivity() as CreateNewTransactionActivity?)?.updateCurCategory(it)
        }
        CoroutineScope(Dispatchers.IO).launch {
            val categoryRepository = CategoryRepository(MyDatabase.getInstance(requireContext()).categoryDao())
            categoryRepository.getCategoryByType(if (isExpense) TYPE_EXPENSE else TYPE_INCOME).let {
                listCategory.addAll(it)
            }
            CoroutineScope(Dispatchers.Main).launch {
                categoryAdapter.setListCategory(listCategory)
                binding?.rvCategory?.adapter = categoryAdapter
            }
        }
    }

    private fun setupView() {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}