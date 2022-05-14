package com.huukhuongit.novelreader.utils

import androidx.recyclerview.widget.RecyclerView
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

class Helpers {

    companion object {

        fun overScroll(recyclerView: RecyclerView, orientation: Int) {
            OverScrollDecoratorHelper.setUpOverScroll(
                recyclerView,
                orientation
            )
        }
    }

}