package com.huukhuongit.novelreader.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.huukhuongit.novelreader.fragments.*

class AdapterViewPagerBottomNavigation(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val COUNT = 5

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> CategoriesFragment()
            2 -> OfflineFragment()
            3 -> NotificationsFragment()
            else -> SettingsFragment()
        }
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Screen " + (position + 1)
    }
}
