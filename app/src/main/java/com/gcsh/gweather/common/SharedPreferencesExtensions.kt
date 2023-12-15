package com.gcsh.gweather.common

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.StringRes
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

fun SharedPreferences.getWeatherList(context: Context, @StringRes keyRes: Int): List<WeatherItem> {
    val key = context.getString(keyRes, "")
    val json = getString(key, "[]") ?: "[]"

    return try {
        val type = object : TypeToken<List<WeatherItem>>() {}.type
        Gson().fromJson(json, type) ?: emptyList()
    } catch (e: JsonSyntaxException) {
        emptyList()
    }
}

fun SharedPreferences.Editor.putWeatherList(
    context: Context, @StringRes keyRes: Int, list: List<WeatherItem>
) {
    val key = context.getString(keyRes, "")
    val json = Gson().toJson(list)
    putString(key, json)
    apply()
}