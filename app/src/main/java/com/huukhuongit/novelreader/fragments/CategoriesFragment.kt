package com.huukhuongit.novelreader.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.huukhuongit.novelreader.adapters.AdapterCategory
import com.huukhuongit.novelreader.adapters.OnItemClickListener
import com.huukhuongit.novelreader.databinding.FragmentCategoriesBinding
import com.huukhuongit.novelreader.models.CategoryModel
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

class CategoriesFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var listCategories: ArrayList<CategoryModel>
    private lateinit var adapterCategory: AdapterCategory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val view = binding.root
        addControls()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addControls() {
        // overscroll recyclerview
        OverScrollDecoratorHelper.setUpOverScroll(
            binding.rcvCategories,
            OverScrollDecoratorHelper.ORIENTATION_VERTICAL
        )

        listCategories = ArrayList()
        for (i in 1..10) {
            listCategories.add(CategoryModel(i, "Danh mục $i", "", false))
        }
        listCategories.add(CategoryModel(11, "Khác", "", false))
        adapterCategory = AdapterCategory(listCategories, this)
        binding.rcvCategories.adapter = adapterCategory
    }

    override fun onItemClick(position: Int, item: Any) {
        val category: CategoryModel = item as CategoryModel
        Toast.makeText(activity, "Item Click: ${category.name}", Toast.LENGTH_SHORT).show()
    }

}