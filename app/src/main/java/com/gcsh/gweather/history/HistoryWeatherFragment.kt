package com.gcsh.gweather.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcsh.gweather.R
import com.gcsh.gweather.common.WeatherItem
import com.gcsh.gweather.common.getWeatherList
import com.gcsh.gweather.databinding.FragmentHistoryWeatherBinding
import com.gcsh.gweather.history.helper.HistoryRecyclerAdapter

class HistoryWeatherFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHistoryWeatherBinding.inflate(inflater, container, false)
        val recyclerView = binding.recyclerViewWeather

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val weatherList = getWeatherFromSharedPreferences(requireContext())
        val adapter = HistoryRecyclerAdapter(weatherList)

        recyclerView.adapter = adapter
        return binding.root
    }

    private fun getWeatherFromSharedPreferences(context: Context): List<WeatherItem> {
        val sharedPreferences = context.getSharedPreferences(
            context.getString(R.string.weather_shared_pref_key),
            Context.MODE_PRIVATE
        )

        return sharedPreferences.getWeatherList(
            context,
            R.string.weather_main_history_key
        )
    }

}