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

    fun signInUser() {
        sharedPreferences.edit().putBoolean(SING_IN, true).apply()
    }

    fun signInUserTrue(): Boolean {
        return sharedPreferences.getBoolean(SING_IN, false)
    }

    fun signInAdmin() {
        sharedPreferences.edit().putBoolean(SING_IN, true).apply()
    }

    fun signInAdminTrue(): Boolean {
        return sharedPreferences.getBoolean(SING_IN, false)
    }

    fun signUpUser() {
        sharedPreferences.edit().putBoolean(SING_IN, true).apply()
    }

    fun signUpUserTrue(): Boolean {
        return sharedPreferences.getBoolean(SING_IN, false)
    }




    companion object {
        const val SHOWED = "SHOWED"
        const val SING_IN = "SINGIN"
    }
}