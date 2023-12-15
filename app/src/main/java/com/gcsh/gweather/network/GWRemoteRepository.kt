package com.gcsh.gweather.network

import com.gcsh.gweather.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

class GWRemoteRepository {

    private val gwApiServices: GWApiServices = RetrofitService.openGWApiServices
    private val apiKey = BuildConfig.GW_KEY
    suspend fun getWeather(lat: Double, lon: Double): NetworkGWData {
        return gwApiServices.getWeather(lat, lon, apiKey)
    }
}

interface GWApiServices {
    @GET("weather")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ): NetworkGWData
}