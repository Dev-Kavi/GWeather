package com.gcsh.gweather.network

import com.gcsh.gweather.common.GWCloudsData
import com.gcsh.gweather.common.GWCoordData
import com.gcsh.gweather.common.GWData
import com.gcsh.gweather.common.GWMainData
import com.gcsh.gweather.common.GWRainData
import com.gcsh.gweather.common.GWSysData
import com.gcsh.gweather.common.GWWeatherData
import com.gcsh.gweather.common.GWWindData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkGWData(
    @Json(name = "coord") var coord: NetworkGWCoordData,

    @Json(name = "weather") var weather: List<NetworkGWWeatherData>,

    @Json(name = "base") var base: String,

    @Json(name = "main") var main: NetworkGWMainData,

    @Json(name = "visibility") var visibility: Int,

    @Json(name = "wind") var wind: NetworkGWWindData,

    @Json(name = "rain") var rain: NetworkGWRainData?,

    @Json(name = "clouds") var clouds: NetworkGWCloudsData,

    @Json(name = "dt") var dt: Int,

    @Json(name = "sys") var sys: NetworkGWSysData,

    @Json(name = "timezone") var timezone: Int,

    @Json(name = "id") var id: Int,

    @Json(name = "name") var name: String,

    @Json(name = "cod") var cod: Int,

    )

fun NetworkGWData.asDomainModel(): GWData = GWData(
    coord = coord.asDomainModel(),
    weather = weather.asDomainModel(),
    base = base,
    main = main.asDomainModel(),
    visibility = visibility,
    wind = wind.asDomainModel(),
    rain = rain?.asDomainModel(),
    clouds = clouds.asDomainModel(),
    dt = dt,
    sys = sys.asDomainModel(),
    timezone = timezone,
    id = id,
    name = name,
    cod = cod
)

@JsonClass(generateAdapter = true)
data class NetworkGWCoordData(
    @Json(name = "lon") var lon: Double,

    @Json(name = "lat") var lat: Double
)

fun NetworkGWCoordData.asDomainModel(): GWCoordData = GWCoordData(
    lon = lon,
    lat = lat
)

@JsonClass(generateAdapter = true)
data class NetworkGWWeatherData(
    @Json(name = "id") var id: Int,

    @Json(name = "main") var main: String,

    @Json(name = "description") var description: String,

    @Json(name = "icon") var icon: String,
)

fun List<NetworkGWWeatherData>.asDomainModel(): List<GWWeatherData> {
    return map {
        GWWeatherData(
            id = it.id,
            main = it.main,
            description = it.description,
            icon = it.icon
        )
    }
}

@JsonClass(generateAdapter = true)
data class NetworkGWMainData(
    @Json(name = "temp") var temp: Double,

    @Json(name = "feels_like") var feelsLike: Double,

    @Json(name = "temp_min") var tempMin: Double,

    @Json(name = "temp_max") var tempMax: Double,

    @Json(name = "pressure") var pressure: Int,

    @Json(name = "humidity") var humidity: Int,

    @Json(name = "sea_level") var seaLevel: Int,

    @Json(name = "grnd_level") var grndLevel: Int,
)

fun NetworkGWMainData.asDomainModel(): GWMainData = GWMainData(
    temp = temp,
    feelsLike = feelsLike,
    tempMin = tempMin,
    tempMax = tempMax,
    pressure = pressure,
    humidity = humidity,
    seaLevel = seaLevel,
    grndLevel = grndLevel
)

@JsonClass(generateAdapter = true)
data class NetworkGWWindData(
    @Json(name = "speed") var speed: Double,

    @Json(name = "deg") var deg: Int,

    @Json(name = "gust") var gust: Double
)

fun NetworkGWWindData.asDomainModel(): GWWindData = GWWindData(
    speed = speed,
    deg = deg,
    gust = gust
)

@JsonClass(generateAdapter = true)
data class NetworkGWRainData(
    @Json(name = "1h") var h1: Double,
)

fun NetworkGWRainData.asDomainModel(): GWRainData = GWRainData(
    h1 = h1
)

@JsonClass(generateAdapter = true)
data class NetworkGWCloudsData(
    @Json(name = "all") var all: Int,
)

fun NetworkGWCloudsData.asDomainModel(): GWCloudsData = GWCloudsData(
    all = all
)

@JsonClass(generateAdapter = true)
data class NetworkGWSysData(
    @Json(name = "type") var type: Int,

    @Json(name = "id") var id: Int,

    @Json(name = "country") var country: String,

    @Json(name = "sunrise") var sunrise: Long,

    @Json(name = "sunset") var sunset: Long
)

fun NetworkGWSysData.asDomainModel(): GWSysData = GWSysData(
    type = type,
    id = id,
    country = country,
    sunrise = sunrise,
    sunset = sunset
)



