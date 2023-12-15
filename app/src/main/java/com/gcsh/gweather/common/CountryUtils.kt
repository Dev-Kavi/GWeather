package com.gcsh.gweather.common

import java.util.Locale

object CountryUtils {
    fun getCountryName(countryCode: String): String {
        return try {
            val locale = Locale("", countryCode)
            val countryDisplayName = locale.displayCountry

            countryDisplayName.ifEmpty {
                "Unknown Country"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Unknown Country"
        }
    }
}