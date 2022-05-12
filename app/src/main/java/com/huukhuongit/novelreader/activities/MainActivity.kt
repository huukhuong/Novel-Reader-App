package com.huukhuongit.novelreader.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.huukhuongit.novelreader.R
import com.huukhuongit.novelreader.databinding.ActivityMainBinding
import com.huukhuongit.novelreader.fragments.HomeFragment

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
                R.id.item_bottom_categories -> Log.e("Item click", "Categories")
                R.id.item_bottom_offline -> Log.e("Item click", "Offline")
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
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

}