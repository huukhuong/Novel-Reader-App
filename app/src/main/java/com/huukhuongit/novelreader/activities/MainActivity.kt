package com.huukhuongit.novelreader.activities

import android.os.Bundle
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.huukhuongit.novelreader.R
import com.huukhuongit.novelreader.adapters.AdapterPortaitNovel
import com.huukhuongit.novelreader.adapters.OnItemClickListener
import com.huukhuongit.novelreader.models.NovelModel
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var scrollView: ScrollView
    private lateinit var txtSearchKeyword: TextView

    private lateinit var carouselDiscover: ImageSlider
    private lateinit var listBannerCarousel: ArrayList<SlideModel>

    private lateinit var rcvPopular: RecyclerView
    private lateinit var listNovelPopular: ArrayList<NovelModel>
    private lateinit var adapterNovelPopular: AdapterPortaitNovel

    private lateinit var rcvRecommended: RecyclerView
    private lateinit var listNovelRecommended: ArrayList<NovelModel>
    private lateinit var adapterNovelRecommended: AdapterPortaitNovel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addControls()
        addEvents()
    }

    private fun addControls() {
        scrollView = findViewById(R.id.scrollView)

        txtSearchKeyword = findViewById(R.id.txtSearchKeyword)
        carouselDiscover = findViewById(R.id.carouselDiscover)
        rcvPopular = findViewById(R.id.rcvPopular)
        rcvRecommended = findViewById(R.id.rcvRecommended)

        // scrollover recyclerview and parent scrollview
        OverScrollDecoratorHelper.setUpOverScroll(scrollView)
        OverScrollDecoratorHelper.setUpOverScroll(
            rcvPopular,
            OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL
        )
        OverScrollDecoratorHelper.setUpOverScroll(
            rcvRecommended,
            OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL
        )

        // setup carousel list
        listBannerCarousel = ArrayList()
        listBannerCarousel.add(SlideModel("https://suckhoedoisong.qltns.mediacdn.vn/Images/phamquynh/2020/05/12/banner_sach_ki_niem_130_nam_ngay_sinh_Chu_tich_Ho_Chi_Minh.jpg"))
        listBannerCarousel.add(SlideModel("https://afamilycdn.com/150157425591193600/2020/3/26/base64-1585158970659821701827.png"))
        listBannerCarousel.add(SlideModel("https://files.giaoducthoidai.vn/Uploaded/huyentt/2019-05-14/bo-ba-trinh-tham-DIMQ.jpg"))
        carouselDiscover.setImageList(listBannerCarousel, ScaleTypes.CENTER_CROP)

        listNovelPopular = ArrayList()
        for (i in 1..10)
            listNovelPopular.add(
                NovelModel(
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
        rcvPopular.adapter = adapterNovelPopular

        listNovelRecommended = ArrayList()
        for (i in 1..10)
            listNovelRecommended.add(
                NovelModel(
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
        rcvRecommended.adapter = adapterNovelRecommended
    }

    private fun addEvents() {

    }

    override fun onItemClick(position: Int, item: Any) {
        val itemNovel: NovelModel = item as NovelModel
        Toast.makeText(this, "Position click $position", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Item click ${itemNovel.name}", Toast.LENGTH_SHORT).show()
    }

}