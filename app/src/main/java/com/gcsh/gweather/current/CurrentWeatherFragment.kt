package com.gcsh.gweather.current

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gcsh.gweather.R
import com.gcsh.gweather.common.CountryUtils
import com.gcsh.gweather.common.DateUtils
import com.gcsh.gweather.common.GWData
import com.gcsh.gweather.common.GWSysData
import com.gcsh.gweather.common.GWWeatherData
import com.gcsh.gweather.common.TemperatureUtils
import com.gcsh.gweather.common.WeatherItem
import com.gcsh.gweather.common.getWeatherList
import com.gcsh.gweather.common.putWeatherList
import com.gcsh.gweather.databinding.FragmentCurrentWeatherBinding
import com.gcsh.gweather.home.helper.HomeViewModel
import com.gcsh.gweather.home.helper.HomeViewModelFactory
import com.gcsh.gweather.home.helper.WeatherState
import com.gcsh.gweather.network.GWRemoteRepository
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {

    private val repository = GWRemoteRepository()
    private val viewModelFactory = HomeViewModelFactory(repository)
    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)

        observeWeatherData(binding)

        val latitude = 14.111512  // Example latitude
        val longitude = 120.984222  // Example longitude
        viewModel.getWeather(latitude, longitude)

        return binding.root
    }

    private fun observeWeatherData(binding: FragmentCurrentWeatherBinding) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weatherData.collect { weatherState ->
                    when (weatherState) {
                        is WeatherState.Loading -> showLoadingUI(binding)

                        is WeatherState.Success -> {
                            hideLoadingUI(binding)
                            updateUIWithData(binding, weatherState.gwData)
                        }

                        is WeatherState.Error -> {
                            hideLoadingUI(binding)
                            handleErrorState(weatherState.errorMessage)
                        }
                    }
                }
            }
        }
    }

    private fun showLoadingUI(binding: FragmentCurrentWeatherBinding) {
        // Implement UI changes to show loading indicator
    }

    private fun hideLoadingUI(binding: FragmentCurrentWeatherBinding) {
        // Implement UI changes to hide loading indicator
    }

    private fun updateUIWithData(
        binding: FragmentCurrentWeatherBinding, weatherResponse: GWData
    ) {
        with(binding) {
            val sys = weatherResponse.sys
            val main = weatherResponse.main
            updateLocationText(binding, weatherResponse)
            saveWeatherToSharedPreferences(requireContext(), weatherResponse.weather)
            updateSunriseSunsetTexts(binding, weatherResponse.sys)
            updateTemperatureText(binding, main.temp)
        }
    }

    private fun updateLocationText(
        binding: FragmentCurrentWeatherBinding, weatherResponse: GWData
    ) {
        binding.tvLocation.text = "%s, %s".format(
            weatherResponse.name, CountryUtils.getCountryName(weatherResponse.sys.country)
        )
    }

    private fun updateSunriseSunsetTexts(
        binding: FragmentCurrentWeatherBinding, sys: GWSysData
    ) {
        binding.tvSunrise.text = getString(
            R.string.weather_sunrise_text, DateUtils.convertUnixTimestampToHourFormat(sys.sunrise)
        )
        binding.tvSunset.text = getString(
            R.string.weather_sunset_text, DateUtils.convertUnixTimestampToHourFormat(sys.sunset)
        )
    }

    private fun updateTemperatureText(
        binding: FragmentCurrentWeatherBinding, temperature: Double
    ) {
        binding.tvTemperature.text = getString(
            R.string.weather_temperature_text,
            TemperatureUtils.kelvinToCelsius(temperature).toString()
        )
    }

    private fun handleErrorState(errorMessage: String) {
        // Implement UI changes or logging for error state
    }


    private fun saveWeatherToSharedPreferences(context: Context, weather: List<GWWeatherData>) {
        val sharedPreferences = context.getSharedPreferences(
            context.getString(R.string.weather_shared_pref_key),
            Context.MODE_PRIVATE
        )

        if (weather.isNotEmpty()) {
            val existingWeatherList = sharedPreferences.getWeatherList(
                context,
                R.string.weather_main_history_key
            ).toMutableList()

            val firstWeatherCondition = weather[0]
            val newWeatherItem = WeatherItem(firstWeatherCondition.main)

            existingWeatherList.add(newWeatherItem)

            sharedPreferences.edit {
                putWeatherList(
                    context,
                    R.string.weather_main_history_key,
                    existingWeatherList
                )
            }
        }
    }
}