package com.gcsh.gweather.home.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gcsh.gweather.network.GWRemoteRepository

class HomeViewModelFactory(private val repository: GWRemoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
