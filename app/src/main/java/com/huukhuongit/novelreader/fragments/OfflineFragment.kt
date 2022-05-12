package com.huukhuongit.novelreader.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.huukhuongit.novelreader.adapters.OnItemClickListener
import com.huukhuongit.novelreader.databinding.FragmentOfflineBinding

class OfflineFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentOfflineBinding? = null
    private val binding get() = _binding!!

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

    }

    override fun onItemClick(position: Int, item: Any) {

    }

}