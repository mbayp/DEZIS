package com.dezis.geeks_dezis.core.common

import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

class PreferenceHelper @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun setOnboardingShown() {
        sharedPreferences.edit().putBoolean(SHOWED, true).apply()
    }

    fun isOnboardingShown(): Boolean {
        return sharedPreferences.getBoolean(SHOWED, false)
    }

    fun signInUser() {
        sharedPreferences.edit().putBoolean(USER_SIGNED_IN, true).apply()
    }

    fun isUserSignedIn(): Boolean {
        return sharedPreferences.getBoolean(USER_SIGNED_IN, false)
    }

    fun signInAdmin() {
        sharedPreferences.edit().putBoolean(ADMIN_SIGNED_IN, true).apply()
    }

    fun isAdminSignedIn(): Boolean {
        return sharedPreferences.getBoolean(ADMIN_SIGNED_IN, false)
    }

    fun signOut() {
        sharedPreferences.edit()
            .putBoolean(USER_SIGNED_IN, false)
            .putBoolean(ADMIN_SIGNED_IN, false)
            .apply()
    }

    fun saveUserId(userId: Int) {
        sharedPreferences.edit().putInt(USER_ID, userId).apply()
        Log.d("PreferenceHelper", "User ID saved: $userId")
    }

    fun getUserId(): Int {
        val userId = sharedPreferences.getInt(USER_ID, -1)
        Log.d("PreferenceHelper", "User ID retrieved: $userId")
        return userId
    }

    companion object {
        private const val SHOWED = "SHOWED"
        private const val USER_SIGNED_IN = "USER_SIGNED_IN"
        private const val ADMIN_SIGNED_IN = "ADMIN_SIGNED_IN"
        private const val USER_ID = "USER_ID"
    }
}
