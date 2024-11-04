package com.dezis.geeks_dezis.core.utils

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceHelper @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun onShowed() {
        sharedPreferences.edit().putBoolean(SHOWED, false).apply()
    }

    fun isShowed(): Boolean {
        return sharedPreferences.getBoolean(SHOWED, true)
    }

    fun singInUser() {
        sharedPreferences.edit().putBoolean(SINGIN, false).apply()
    }

    fun singInUserTrue(): Boolean {
        return sharedPreferences.getBoolean(SINGIN, true)
    }

    fun singInAdmin() {
        sharedPreferences.edit().putBoolean(SINGIN, false).apply()
    }

    fun singInAdminTrue(): Boolean {
        return sharedPreferences.getBoolean(SINGIN, true)
    }


    companion object {
        const val SHOWED = "SHOWED"
        const val SINGIN = "SINGIN"
    }
}