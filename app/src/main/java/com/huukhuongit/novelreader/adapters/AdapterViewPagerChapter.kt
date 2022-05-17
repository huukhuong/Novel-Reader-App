package com.huukhuongit.novelreader.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.huukhuongit.novelreader.fragments.ChapterFragment
import com.huukhuongit.novelreader.models.ChapterModel

class AdapterViewPagerChapter(activity: AppCompatActivity, private val itemsCount: Int) : FragmentStateAdapter(activity) {

    private lateinit var listChapers: ArrayList<ChapterModel>

    fun setListChapters(listChapters:ArrayList<ChapterModel>) {
        this.listChapers = listChapters
    }

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        return ChapterFragment(listChapers[position])
    }

}
