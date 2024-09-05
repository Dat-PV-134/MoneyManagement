package com.rekoj134.moneymanagement.screen.create_new_transaction

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rekoj134.moneymanagement.databinding.ItemCategoryInDialogBinding
import com.rekoj134.moneymanagement.model.Category
import com.rekoj134.moneymanagement.util.CategoryUtil

class CategoryInDialogAdapter(private val context: Context) :
    RecyclerView.Adapter<CategoryInDialogViewHolder>() {

    private val listCategory by lazy { ArrayList<Category>() }

    fun setListCategory(listCategory: List<Category>) {
        this.listCategory.clear()
        this.listCategory.addAll(listCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryInDialogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryInDialogBinding.inflate(inflater)
        return CategoryInDialogViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }

    override fun onBindViewHolder(holder: CategoryInDialogViewHolder, position: Int) {
        holder.bind(context, listCategory[position], position)
    }
}

class CategoryInDialogViewHolder(private val binding: ItemCategoryInDialogBinding) : ViewHolder(binding.root) {
    fun bind(context: Context, category: Category, position: Int) {
        binding.tvCategoryName.text = category.name
        CategoryUtil.getCategoryId(category.icon)?.let {
            binding.imgCategory.setImageResource(it)
        }
        binding.imgCategory.backgroundTintList = ColorStateList.valueOf(Color.parseColor(category.iconColor))
    }
}
