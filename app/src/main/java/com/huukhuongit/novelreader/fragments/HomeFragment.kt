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
import kotlin.collections.ArrayList


class HomeFragment : Fragment(), OnItemClickListener {

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

    private lateinit var listNovelTop10: ArrayList<NovelModel>
    private lateinit var adapterNovelTop10: AdapterLandscapeNovel

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
        getTop10Novels()
        getListPopular()
        getListRecent()
        getListRecommended()
    }

    private fun getListBanners() {
        retrofit.getListBanners()
            .enqueue(object : Callback<ArrayList<BannerModel>?> {
                override
                fun onResponse(
                    call: Call<ArrayList<BannerModel>?>,
                    response: Response<ArrayList<BannerModel>?>
                ) {
                    val body = response.body()
                    if (body != null) {
                        listBannerCarousel = ArrayList()
                        for (item in body) {
                            listBannerCarousel.add(SlideModel(item.thumbnail))
                            item.thumbnail?.let { Log.e("ITEM", it) }
                        }
                        binding.carouselDiscover.setImageList(
                            listBannerCarousel,
                            ScaleTypes.CENTER_CROP
                        )
                    }
                }

                override
                fun onFailure(call: Call<ArrayList<BannerModel>?>, t: Throwable) {
                    Log.e("activity", Log.getStackTraceString(t.cause))
                }
            })
    }

    private fun getTop10Novels() {
        listNovelTop10 = ArrayList()
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
                            item.thumbnail?.let { Log.e("ITEM", it) }
                        }
                        binding.carouselDiscover.setImageList(
                            listBannerCarousel,
                            ScaleTypes.CENTER_CROP
                        )
                    }
                }

                override
                fun onFailure(call: Call<ArrayList<BannerModel>?>, t: Throwable) {
                    Log.e("activity", Log.getStackTraceString(t.cause))
                }
            })
        adapterNovelTop10 =
            AdapterLandscapeNovel(R.layout.item_novel_vertical, listNovelTop10, this)
        binding.rcvTop10.adapter = adapterNovelTop10
    }

    private fun getListPopular() {
        listNovelPopular = ArrayList()
        for (i in 1..10)
            listNovelPopular.add(
                NovelModel(
                    id = i,
                    thumbnail = "https://307a0e78.vws.vegacdn.vn/view/v2/image/img.book/0/0/0/19167.jpg?v=1&w=340&h=497",
                    name = "Nơi giấc mơ em thuộc về",
                    author = "Nhóm 4.0",
                    description = "description",
                    chapters = 16,
                    isDone = false,
                    uploadedAt = Date(),
                    updatedAt = Date(),
                    isDeleted = false
                )
            )
        adapterNovelPopular =
            AdapterPortaitNovel(R.layout.item_novel_portait, listNovelPopular, this)
        binding.rcvPopular.adapter = adapterNovelPopular
    }

    private fun getListRecent() {
        listNovelRecent = ArrayList()
        for (i in 1..10)
            listNovelRecent.add(
                NovelModel(
                    id = i,
                    thumbnail = "https://www.nxbtre.com.vn/Images/Book/nxbtre_full_04152018_031555.jpg",
                    name = "Tôi thấy hoa vàng trên cỏ xanh",
                    author = "Nguyễn Nhật Ánh",
                    description = "“Tôi thấy hoa vàng trên cỏ xanh” đúng là một vé để bạn đọc được trở về tuổi thơ. Cùng lũ trẻ trong xóm, ngày ngày rủ nhau chơi các trò chơi mà chỉ có trẻ con thôn quê mới có được. Những câu chuyện nhỏ gần gũi, những tình cảm ấm áp mà những đứa trẻ dành cho nhau khi mới chỉ chớm biết cái thứ tình cảm khác với tình bạn bè.",
                    chapters = 16,
                    isDone = false,
                    uploadedAt = Date(),
                    updatedAt = Date(),
                    isDeleted = false
                )
            )
        adapterNovelRecent =
            AdapterLandscapeNovel(R.layout.item_novel_landscape, listNovelRecent, this)
        binding.rcvRecent.adapter = adapterNovelRecent
    }

    private fun getListRecommended() {
        listNovelRecommended = ArrayList()
        for (i in 1..10)
            listNovelRecommended.add(
                NovelModel(
                    id = i,
                    thumbnail = "https://307a0e78.vws.vegacdn.vn/view/v2/image/img.book/0/0/0/19167.jpg?v=1&w=340&h=497",
                    name = "Nơi giấc mơ em thuộc về",
                    author = "Nhóm 4.0",
                    description = "description",
                    chapters = 16,
                    isDone = false,
                    uploadedAt = Date(),
                    updatedAt = Date(),
                    isDeleted = false
                )
            )
        adapterNovelRecommended =
            AdapterPortaitNovel(R.layout.item_novel_portait, listNovelRecommended, this)
        binding.rcvRecommended.adapter = adapterNovelRecommended
    }

    override fun onItemClick(position: Int, item: Any) {
        val itemNovel: NovelModel = item as NovelModel
        Toast.makeText(activity, "Item click ${itemNovel.name}", Toast.LENGTH_SHORT).show()
    }
}