package com.gcsh.gweather.home.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcsh.gweather.common.GWData
import com.gcsh.gweather.network.GWRemoteRepository
import com.gcsh.gweather.network.NetworkGWData
import com.gcsh.gweather.network.asDomainModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: GWRemoteRepository
) : ViewModel() {
    private val _weatherData = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherData: StateFlow<WeatherState> get() = _weatherData

    fun getWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                val response = repository.getWeather(latitude, longitude)
                _weatherData.value = WeatherState.Success(response.asDomainModel())
            } catch (e: Exception) {
                _weatherData.value = WeatherState.Error("Failed to get weather data")
                e.printStackTrace()
            }
        }
    }
}

sealed class WeatherState {
    data object Loading : WeatherState()
    data class Success(val gwData: GWData) : WeatherState()
    data class Error(val errorMessage: String) : WeatherState()
}