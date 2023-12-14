package com.gcsh.gweather.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.gcsh.gweather.databinding.ActivityHomeWeatherBinding
import com.gcsh.gweather.home.helper.HomePageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeWeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager: ViewPager2 = binding.homeViewPager
        val tabLayout: TabLayout = binding.homeTabLayout

        viewPager.adapter = HomePageAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Current Weather"
                1 -> "History Weather"
                else -> ""
            }
        }.attach()
    }

}