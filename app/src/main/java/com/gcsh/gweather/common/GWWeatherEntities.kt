package com.gcsh.gweather.common

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GWData(
    var coord: GWCoordData,
    var weather: List<GWWeatherData>,
    var base: String,
    var main: GWMainData,
    var visibility: Int,
    var wind: GWWindData,
    var rain: GWRainData?,
    var clouds: GWCloudsData,
    var dt: Int,
    var sys: GWSysData,
    var timezone: Int,
    var id: Int,
    var name: String,
    var cod: Int,

    )

@JsonClass(generateAdapter = true)
data class GWCoordData(
    var lon: Double,
    var lat: Double
)

@JsonClass(generateAdapter = true)
data class GWWeatherData(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String,
)

@JsonClass(generateAdapter = true)
data class GWMainData(
    var temp: Double,
    var feelsLike: Double,
    var tempMin: Double,
    var tempMax: Double,
    var pressure: Int,
    var humidity: Int,
    var seaLevel: Int,
    var grndLevel: Int,
)

@JsonClass(generateAdapter = true)
data class GWWindData(
    var speed: Double,
    var deg: Int,
    var gust: Double
)

@JsonClass(generateAdapter = true)
data class GWRainData(
    var h1: Double,
)

@JsonClass(generateAdapter = true)
data class GWCloudsData(
    var all: Int,
)

@JsonClass(generateAdapter = true)
data class GWSysData(
    var type: Int,
    var id: Int,
    var country: String,
    var sunrise: Long,
    var sunset: Long
)

data class WeatherItem(val weather: String)