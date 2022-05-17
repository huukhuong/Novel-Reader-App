package com.huukhuongit.novelreader.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.huukhuongit.novelreader.databinding.FragmentChapterBinding
import com.huukhuongit.novelreader.models.ChapterModel

class ChapterFragment(chapterModel: ChapterModel) : Fragment() {

    private var chapterModel: ChapterModel = chapterModel

    private var _binding: FragmentChapterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChapterBinding.inflate(inflater, container, false)
        val view = binding.root

        addControls()

        return view
    }

    private fun addControls() {
        binding.txtTitle.text = chapterModel.title
        binding.txtContent.text = chapterModel.content
    }

}