package com.gcsh.gweather.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.gcsh.gweather.R
import com.gcsh.gweather.databinding.ActivityHomeWeatherBinding
import com.gcsh.gweather.home.helper.HomePageAdapter
import com.gcsh.gweather.home.helper.HomeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeWeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val viewPager: ViewPager2 = homeViewPager
            val tabLayout: TabLayout = homeTabLayout

            viewPager.adapter = HomePageAdapter(supportFragmentManager, lifecycle)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> getString(R.string.current_weather)
                    1 -> getString(R.string.history_weather)
                    else -> ""
                }
            }.attach()
        }
    }

}