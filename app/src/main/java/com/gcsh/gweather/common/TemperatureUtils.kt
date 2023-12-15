package com.gcsh.gweather.common

import kotlin.math.round

object TemperatureUtils {

    fun kelvinToCelsius(kelvinTemperature: Double): Double {
        val celsiusTemperature = kelvinTemperature - 273.15
        return round(celsiusTemperature * 100) / 100
    }
}