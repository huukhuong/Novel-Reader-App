package com.huukhuongit.novelreader.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.huukhuongit.novelreader.R
import com.huukhuongit.novelreader.adapters.AdapterLandscapeNovel
import com.huukhuongit.novelreader.adapters.AdapterPortaitNovel
import com.huukhuongit.novelreader.adapters.OnItemClickListener
import com.huukhuongit.novelreader.databinding.FragmentHomeBinding
import com.huukhuongit.novelreader.models.BannerModel
import com.huukhuongit.novelreader.models.NovelModel
import com.huukhuongit.novelreader.network.APIService
import com.huukhuongit.novelreader.utils.Constants
import com.huukhuongit.novelreader.utils.Helpers
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class HomeFragment : Fragment(), OnItemClickListener {

    private val listener = this

    private val retrofit = Constants.retrofit.create(APIService::class.java)

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var listBannerCarousel: ArrayList<SlideModel>

    private lateinit var listNovelPopular: ArrayList<NovelModel>
    private lateinit var adapterNovelPopular: AdapterPortaitNovel

    private lateinit var listNovelRecommended: ArrayList<NovelModel>
    private lateinit var adapterNovelRecommended: AdapterPortaitNovel

    private lateinit var listNovelRecent: ArrayList<NovelModel>
    private lateinit var adapterNovelRecent: AdapterLandscapeNovel

    private lateinit var listNovelTop: ArrayList<NovelModel>
    private lateinit var adapterNovelTop: AdapterLandscapeNovel

    private var isLoadBanner = true
    private var isLoadTop5Novels = true
    private var isLoadListPopular = true
    private var isLoadListRecent = true
    private var isLoadListRecommended = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        addControls()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addControls() {
        binding.loading.root.visibility = View.VISIBLE
        binding.scrollView.visibility = View.GONE

        Helpers.overScroll(
            binding.rcvPopular,
            OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL
        )
        Helpers.overScroll(
            binding.rcvRecent,
            OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL
        )
        Helpers.overScroll(
            binding.rcvRecommended,
            OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL
        )

        getListBanners()
        getTop5Novels()
        getListPopular()
        getListRecent()
        getListRecommended()
    }

    private fun getListBanners() {
        listBannerCarousel = ArrayList()
        retrofit.getListBanners()
            .enqueue(object : Callback<ArrayList<BannerModel>?> {
                override
                fun onResponse(
                    call: Call<ArrayList<BannerModel>?>,
                    response: Response<ArrayList<BannerModel>?>
                ) {
                    val body = response.body()
                    if (body != null) {
                        for (item in body) {
                            listBannerCarousel.add(SlideModel(item.thumbnail))
                        }
                        binding.carouselDiscover.setImageList(
                            listBannerCarousel,
                            ScaleTypes.CENTER_CROP
                        )
                        isLoadBanner = false
                        checkLoading()
                    }
                }

                override
                fun onFailure(call: Call<ArrayList<BannerModel>?>, t: Throwable) {
                    Log.e("activity", Log.getStackTraceString(t.cause))
                }
            })
    }

    private fun getTop5Novels() {
        listNovelTop = ArrayList()
        retrofit.getTop5Novels()
            .enqueue(object : Callback<ArrayList<NovelModel>?> {
                override
                fun onResponse(
                    call: Call<ArrayList<NovelModel>?>,
                    response: Response<ArrayList<NovelModel>?>
                ) {
                    Log.e("REQUEST CODE", "${response.code()}")
                    val body = response.body()
                    if (body != null) {
                        listNovelTop.clear()
                        listNovelTop.addAll(body)
                        adapterNovelTop =
                            AdapterLandscapeNovel(
                                R.layout.item_novel_vertical_list,
                                listNovelTop,
                                listener
                            )
                        binding.rcvTop10.adapter = adapterNovelTop
                        isLoadTop5Novels = false
                        checkLoading()
                    }
                }

                override
                fun onFailure(call: Call<ArrayList<NovelModel>?>, t: Throwable) {
                    Log.e("activity", Log.getStackTraceString(t.cause))
                }
            })
    }

    private fun getListPopular() {
        listNovelPopular = ArrayList()
        retrofit.getTopPopularNovels()
            .enqueue(object : Callback<ArrayList<NovelModel>?> {
                override
                fun onResponse(
                    call: Call<ArrayList<NovelModel>?>,
                    response: Response<ArrayList<NovelModel>?>
                ) {
                    Log.e("REQUEST CODE", "${response.code()}")
                    val body = response.body()
                    if (body != null) {
                        listNovelPopular.clear()
                        listNovelPopular.addAll(body)
                        adapterNovelPopular =
                            AdapterPortaitNovel(
                                R.layout.item_novel_portait,
                                listNovelPopular,
                                listener
                            )
                        binding.rcvPopular.adapter = adapterNovelPopular
                        isLoadListPopular = false
                        checkLoading()
                    }
                }

                override
                fun onFailure(call: Call<ArrayList<NovelModel>?>, t: Throwable) {
                    Log.e("activity", Log.getStackTraceString(t.cause))
                }
            })
    }

    private fun getListRecent() {
        listNovelRecent = ArrayList()
        adapterNovelRecent =
            AdapterLandscapeNovel(R.layout.item_novel_landscape, listNovelRecent, listener)
        binding.rcvRecent.adapter = adapterNovelRecent
        isLoadListRecent = false
        checkLoading()
    }

    private fun getListRecommended() {
        listNovelRecommended = ArrayList()
        retrofit.getTopRecommendedNovels()
            .enqueue(object : Callback<ArrayList<NovelModel>?> {
                override
                fun onResponse(
                    call: Call<ArrayList<NovelModel>?>,
                    response: Response<ArrayList<NovelModel>?>
                ) {
                    Log.e("REQUEST CODE", "${response.code()}")
                    val body = response.body()
                    if (body != null) {
                        listNovelRecommended.clear()
                        listNovelRecommended.addAll(body)
                        adapterNovelRecommended =
                            AdapterPortaitNovel(
                                R.layout.item_novel_portait,
                                listNovelRecommended,
                                listener
                            )
                        binding.rcvRecommended.adapter = adapterNovelRecommended
                        isLoadListRecommended = false
                        checkLoading()
                    }
                }

                override
                fun onFailure(call: Call<ArrayList<NovelModel>?>, t: Throwable) {
                    Log.e("activity", Log.getStackTraceString(t.cause))
                }
            })
    }

    private fun checkLoading() {
        if (!isLoadBanner && !isLoadTop5Novels && !isLoadListPopular && !isLoadListRecent && !isLoadListPopular) {
            binding.loading.root.visibility = View.GONE
            binding.scrollView.visibility = View.VISIBLE
        }
    }

    override fun onItemClick(position: Int, item: Any) {
        val itemNovel: NovelModel = item as NovelModel

    }
}