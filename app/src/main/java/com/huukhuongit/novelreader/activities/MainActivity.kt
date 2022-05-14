package com.huukhuongit.novelreader.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.huukhuongit.novelreader.R
import com.huukhuongit.novelreader.adapters.AdapterViewPagerBottomNavigation
import com.huukhuongit.novelreader.databinding.ActivityMainBinding
import com.huukhuongit.novelreader.fragments.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var fragmentsList: ArrayList<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addControls()
        addEvents()
    }

    private fun addControls() {
        fragmentsList = ArrayList()
        fragmentsList.add(HomeFragment())
        fragmentsList.add(CategoriesFragment())
        fragmentsList.add(OfflineFragment())
        fragmentsList.add(NotificationsFragment())
        fragmentsList.add(SettingsFragment())
        binding.frameLayout.adapter = AdapterViewPagerBottomNavigation(supportFragmentManager)
    }

    private fun addEvents() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_bottom_home -> {
                    replaceFragment(R.id.item_bottom_home)
                    setSearchBarShow(true)
                }
                R.id.item_bottom_categories -> {
                    replaceFragment(R.id.item_bottom_categories)
                    setSearchBarShow(true)
                }
                R.id.item_bottom_offline -> {
                    replaceFragment(R.id.item_bottom_offline)
                    setSearchBarShow(false)
                }
                R.id.item_bottom_notifications -> {
                    replaceFragment(R.id.item_bottom_notifications)
                    setSearchBarShow(false)
                }
                R.id.item_bottom_settings -> {
                    replaceFragment(R.id.item_bottom_settings)
                    setSearchBarShow(false)
                }
                else -> {
                    Log.e("Item click", "No item")
                }
            }
            return@setOnItemSelectedListener true
        }

        binding.frameLayout.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                binding.bottomNavigationView.selectedItemId = when (position) {
                    0 -> R.id.item_bottom_home
                    1 -> R.id.item_bottom_categories
                    2 -> R.id.item_bottom_offline
                    3 -> R.id.item_bottom_notifications
                    else -> R.id.item_bottom_settings
                }
            }

        })
    }

    private fun replaceFragment(itemId: Int) {
        binding.frameLayout.currentItem = when (itemId) {
            R.id.item_bottom_home -> 0
            R.id.item_bottom_categories -> 1
            R.id.item_bottom_offline -> 2
            R.id.item_bottom_notifications -> 3
            else -> 4
        }
    }

    private fun setSearchBarShow(isShow: Boolean) {
        if (!isShow) {
            binding.containerSearch.visibility = View.GONE
        } else {
            binding.containerSearch.visibility = View.VISIBLE
        }
    }

}