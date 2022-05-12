package com.huukhuongit.novelreader.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.huukhuongit.novelreader.R
import com.huukhuongit.novelreader.databinding.ActivityMainBinding
import com.huukhuongit.novelreader.fragments.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currenFragmentId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment(), R.id.item_bottom_home)
        addEvents()
    }

    private fun addEvents() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_bottom_home -> {
                    replaceFragment(HomeFragment(), R.id.item_bottom_home)
                    setSearchBarShow(true)
                }
                R.id.item_bottom_categories -> {
                    replaceFragment(CategoriesFragment(), R.id.item_bottom_categories)
                    setSearchBarShow(true)
                }
                R.id.item_bottom_offline -> {
                    replaceFragment(OfflineFragment(), R.id.item_bottom_offline)
                    setSearchBarShow(false)
                }
                R.id.item_bottom_notifications -> {
                    replaceFragment(NotificationsFragment(), R.id.item_bottom_notifications)
                    setSearchBarShow(false)
                }
                R.id.item_bottom_settings -> {
                    replaceFragment(SettingsFragment(), R.id.item_bottom_settings)
                    setSearchBarShow(false)
                }
                else -> {
                    Log.e("Item click", "No item")
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun replaceFragment(fragment: Fragment, id: Int) {
        if (currenFragmentId != id) {
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(
                R.anim.fragment_slide_in,
                R.anim.fragment_fade_out,
                R.anim.fragment_fade_in,
                R.anim.fragment_slide_out
            )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.replace(R.id.frameLayout, fragment)
            fragmentTransaction.commit()
            currenFragmentId = id
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