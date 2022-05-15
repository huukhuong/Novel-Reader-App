package com.huukhuongit.novelreader.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.huukhuongit.novelreader.adapters.AdapterCategory
import com.huukhuongit.novelreader.adapters.OnItemClickListener
import com.huukhuongit.novelreader.databinding.FragmentCategoriesBinding
import com.huukhuongit.novelreader.models.BannerModel
import com.huukhuongit.novelreader.models.CategoryModel
import com.huukhuongit.novelreader.network.APIService
import com.huukhuongit.novelreader.utils.Constants
import com.huukhuongit.novelreader.utils.Helpers
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesFragment : Fragment(), OnItemClickListener {

    private val listener = this

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
        Helpers.overScroll(binding.rcvCategories, OverScrollDecoratorHelper.ORIENTATION_VERTICAL)

        if (activity?.let { Helpers.isOnline(it) } == true) {
            binding.containerLayout.visibility = View.GONE
            binding.noNetwork.root.visibility = View.GONE
            binding.loading.root.visibility = View.VISIBLE
            getAllCategories()
        } else {
            binding.containerLayout.visibility = View.GONE
            binding.loading.root.visibility = View.GONE
            binding.noNetwork.root.visibility = View.VISIBLE
        }

    }

    private fun getAllCategories() {
        listCategories = ArrayList()
        val retrofit = Constants.retrofit.create(APIService::class.java)
        retrofit.getAllCategory()
            .enqueue(object : Callback<ArrayList<CategoryModel>?> {
                override
                fun onResponse(
                    call: Call<ArrayList<CategoryModel>?>,
                    response: Response<ArrayList<CategoryModel>?>
                ) {
                    val body = response.body()
                    Log.e("CODE", "${response.code()}")
                    if (body != null) {
                        listCategories.addAll(body)
                        adapterCategory = AdapterCategory(listCategories, listener)
                        binding.rcvCategories.adapter = adapterCategory
                    }
                    binding.containerLayout.visibility = View.VISIBLE
                    binding.loading.root.visibility = View.GONE
                }

                override
                fun onFailure(call: Call<ArrayList<CategoryModel>?>, t: Throwable) {
                    Log.e("activity", Log.getStackTraceString(t.cause))
                }
            })
    }

    override fun onItemClick(position: Int, item: Any) {
        val category: CategoryModel = item as CategoryModel
        Toast.makeText(activity, "Item Click: ${category.name}", Toast.LENGTH_SHORT).show()
    }

}