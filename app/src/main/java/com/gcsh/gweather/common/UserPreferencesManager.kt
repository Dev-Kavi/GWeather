package com.gcsh.gweather.common

import android.content.Context
import android.content.SharedPreferences

class UserPreferencesManager(private val context: Context) {

    companion object {
        private const val PREF_NAME = "user_preferences"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"

    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveUserData(username: String, password: String) {
        sharedPreferences.edit().apply {
            putString(KEY_USERNAME, username)
            putString(KEY_PASSWORD, password)
            apply()
        }
    }

    fun getUsername(): String? {
        return sharedPreferences.getString(KEY_USERNAME, null)
    }

    fun getPassword(): String? {
        return sharedPreferences.getString(KEY_PASSWORD, null)
    }
}