package com.huukhuongit.novelreader.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.huukhuongit.novelreader.R
import com.huukhuongit.novelreader.adapters.AdapterPortaitNovel
import com.huukhuongit.novelreader.adapters.OnItemClickListener
import com.huukhuongit.novelreader.databinding.FragmentOfflineBinding
import com.huukhuongit.novelreader.models.NovelModel
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import java.util.*
import kotlin.collections.ArrayList

class OfflineFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentOfflineBinding? = null
    private val binding get() = _binding!!

    private lateinit var listOfflineNovels: ArrayList<NovelModel>
    private lateinit var adapterListOfflineNovel: AdapterPortaitNovel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOfflineBinding.inflate(inflater, container, false)
        val view = binding.root
        addControls()
        return view
    }

    private fun addControls() {
        // overscroll recyclerview
        OverScrollDecoratorHelper.setUpOverScroll(
            binding.rcvOfflineNovels,
            OverScrollDecoratorHelper.ORIENTATION_VERTICAL
        )
        listOfflineNovels = ArrayList()
        for (i in 1..10)
            listOfflineNovels.add(
                NovelModel(
                    id = i,
                    thumbnail = "https://307a0e78.vws.vegacdn.vn/view/v2/image/img.book/0/0/0/19167.jpg?v=1&w=340&h=497",
                    name = "Nơi giấc mơ em thuộc về",
                    author = "Nhóm 4.0",
                    description = "Truyện kể về tình yêu và cuộc sống của hai nhân vật Tiểu Vũ và Phôn.\n" +
                            "Tiểu Vũ mất cha vì một tai nạn giao thông, chính mẹ cô đã nuôi cô khôn lớn.\n" +
                            "Còn Phôn đã gặp Tiểu Vũ vào cái ngày ma cô mất đi người cha thân yêu của mình.\n" +
                            "Hai người họ sau đó không còn lần gặp gỡ nào nữa cho đến khi cả hai đi học ở trời Tây.\n" +
                            "Tình yêu đến với họ thật nhẹ nhàng. Hai người sẵn sàng hi sinh bất cứ thứ gì vì nhau, cùng nhau vượt mọi khó khăn gian khổ.\n" +
                            "Nhưng sóng gió vẫn xảy ra, liệu họ có được hạnh phúc vẹn tròn?",
                    chapters = 16,
                    isDone = false,
                    uploadedAt = Date(),
                    updatedAt = Date(),
                    isDeleted = false
                )
            )
        adapterListOfflineNovel =
            AdapterPortaitNovel(R.layout.item_novel_portait_offline, listOfflineNovels, this)
        binding.rcvOfflineNovels.adapter = adapterListOfflineNovel

        checkEmpty()
    }

    private fun checkEmpty() {
        if (listOfflineNovels.size == 0) {
            binding.layoutEmpty.visibility = View.VISIBLE
            binding.rcvOfflineNovels.visibility = View.GONE
        }
    }

    override fun onItemClick(position: Int, item: Any) {

    }

}