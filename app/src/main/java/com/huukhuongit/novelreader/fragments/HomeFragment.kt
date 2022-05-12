package com.huukhuongit.novelreader.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.huukhuongit.novelreader.R
import com.huukhuongit.novelreader.activities.MainActivity
import com.huukhuongit.novelreader.adapters.AdapterLandscapeNovel
import com.huukhuongit.novelreader.adapters.AdapterPortaitNovel
import com.huukhuongit.novelreader.adapters.OnItemClickListener
import com.huukhuongit.novelreader.databinding.FragmentHomeBinding
import com.huukhuongit.novelreader.models.NovelModel
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import java.util.Date

class HomeFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

    private lateinit var listBannerCarousel: ArrayList<SlideModel>

    private lateinit var listNovelPopular: ArrayList<NovelModel>
    private lateinit var adapterNovelPopular: AdapterPortaitNovel

    private lateinit var listNovelRecommended: ArrayList<NovelModel>
    private lateinit var adapterNovelRecommended: AdapterPortaitNovel

    private lateinit var listNovelRecent: ArrayList<NovelModel>
    private lateinit var adapterNovelRecent: AdapterLandscapeNovel

    private lateinit var listNovelTop10: ArrayList<NovelModel>
    private lateinit var adapterNovelTop10: AdapterLandscapeNovel

    private fun addControls() {
        // overscroll recyclerview
        OverScrollDecoratorHelper.setUpOverScroll(
            binding.rcvPopular,
            OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL
        )
        OverScrollDecoratorHelper.setUpOverScroll(
            binding.rcvRecent,
            OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL
        )
        OverScrollDecoratorHelper.setUpOverScroll(
            binding.rcvRecommended,
            OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL
        )

        // setup carousel list
        listBannerCarousel = ArrayList()
        listBannerCarousel.add(SlideModel("https://suckhoedoisong.qltns.mediacdn.vn/Images/phamquynh/2020/05/12/banner_sach_ki_niem_130_nam_ngay_sinh_Chu_tich_Ho_Chi_Minh.jpg"))
        listBannerCarousel.add(SlideModel("https://afamilycdn.com/150157425591193600/2020/3/26/base64-1585158970659821701827.png"))
        listBannerCarousel.add(SlideModel("https://files.giaoducthoidai.vn/Uploaded/huyentt/2019-05-14/bo-ba-trinh-tham-DIMQ.jpg"))
        binding.carouselDiscover.setImageList(listBannerCarousel, ScaleTypes.CENTER_CROP)

        // setup for Popular
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
                    isPopular = true,
                    isRecommended = true,
                    uploadedAt = Date(),
                    updatedAt = Date(),
                    isDeleted = false
                )
            )
        adapterNovelPopular = AdapterPortaitNovel(listNovelPopular, this)
        binding.rcvPopular.adapter = adapterNovelPopular

        // setup for Recommended
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
                    isPopular = true,
                    isRecommended = true,
                    uploadedAt = Date(),
                    updatedAt = Date(),
                    isDeleted = false
                )
            )
        adapterNovelRecommended = AdapterPortaitNovel(listNovelRecommended, this)
        binding.rcvRecommended.adapter = adapterNovelRecommended

        // setup for Recent
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
                    isPopular = true,
                    isRecommended = true,
                    uploadedAt = Date(),
                    updatedAt = Date(),
                    isDeleted = false
                )
            )
        adapterNovelRecent =
            AdapterLandscapeNovel(R.layout.item_novel_landscape, listNovelRecent, this)
        binding.rcvRecent.adapter = adapterNovelRecent

        // setup for Top 10
        listNovelTop10 = ArrayList()
        for (i in 1..5)
            listNovelTop10.add(
                NovelModel(
                    id = i,
                    thumbnail = "https://www.nxbtre.com.vn/Images/Book/nxbtre_full_04152018_031555.jpg",
                    name = "Tôi thấy hoa vàng trên cỏ xanh",
                    author = "Nguyễn Nhật Ánh",
                    description = "“Tôi thấy hoa vàng trên cỏ xanh” đúng là một vé để bạn đọc được trở về tuổi thơ. Cùng lũ trẻ trong xóm, ngày ngày rủ nhau chơi các trò chơi mà chỉ có trẻ con thôn quê mới có được. Những câu chuyện nhỏ gần gũi, những tình cảm ấm áp mà những đứa trẻ dành cho nhau khi mới chỉ chớm biết cái thứ tình cảm khác với tình bạn bè.",
                    chapters = 16,
                    isDone = false,
                    isPopular = true,
                    isRecommended = true,
                    uploadedAt = Date(),
                    updatedAt = Date(),
                    isDeleted = false
                )
            )
        adapterNovelTop10 =
            AdapterLandscapeNovel(R.layout.item_novel_vertical, listNovelTop10, this)
        binding.rcvTop10.adapter = adapterNovelTop10
    }

    override fun onItemClick(position: Int, item: Any) {
        val itemNovel: NovelModel = item as NovelModel
        Toast.makeText(activity, "Item click ${itemNovel.name}", Toast.LENGTH_SHORT).show()
    }
}