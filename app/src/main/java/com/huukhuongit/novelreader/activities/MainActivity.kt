package com.huukhuongit.novelreader.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.huukhuongit.novelreader.R
import com.huukhuongit.novelreader.databinding.ActivityMainBinding
import com.huukhuongit.novelreader.fragments.CategoriesFragment
import com.huukhuongit.novelreader.fragments.HomeFragment
import com.huukhuongit.novelreader.fragments.OfflineFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())
        addEvents()
    }

    private fun addEvents() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_bottom_home -> replaceFragment(HomeFragment())
                R.id.item_bottom_categories -> replaceFragment(CategoriesFragment())
                R.id.item_bottom_offline -> replaceFragment(OfflineFragment())
                R.id.item_bottom_notifications -> Log.e("Item click", "Notifications")
                R.id.item_bottom_settings -> Log.e("Item click", "Settings")
                else -> {
                    Log.e("Item click", "No item")
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
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
    }

}