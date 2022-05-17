package com.huukhuongit.novelreader.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.huukhuongit.novelreader.adapters.AdapterViewPagerChapter
import com.huukhuongit.novelreader.databinding.ActivityReadChapterBinding
import com.huukhuongit.novelreader.fragments.ChapterFragment
import com.huukhuongit.novelreader.models.ChapterModel
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer2


class ReadChapterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadChapterBinding

    private lateinit var listChapters: ArrayList<ChapterModel>

    private lateinit var fragmentsList: ArrayList<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReadChapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listChapters = intent.getSerializableExtra("listChapters") as ArrayList<ChapterModel>

        fragmentsList = ArrayList()
        for (item in listChapters) {
            fragmentsList.add(ChapterFragment(item))
        }
        val adapter = AdapterViewPagerChapter(this, listChapters.size)
        adapter.setListChapters(listChapters)
        binding.viewPager.adapter = adapter

        // book flip animation
//        val bookFlipPageTransformer = BookFlipPageTransformer2()
//        binding.viewPager.setPageTransformer(bookFlipPageTransformer)
    }

}