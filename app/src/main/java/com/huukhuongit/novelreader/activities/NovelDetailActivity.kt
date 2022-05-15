package com.huukhuongit.novelreader.activities

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.huukhuongit.novelreader.databinding.ActivityNovelDetailBinding


class NovelDetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityNovelDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNovelDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        addControls()
        addEvents()
    }

    private fun addControls() {
    }

    private fun addEvents() {

    }

}