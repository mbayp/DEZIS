package com.dezis.geeks_dezis.presentation.fragments.profile.profile

import android.content.Context
import com.dezis.geeks_dezis.core.base.BaseViewModel

class ProfileViewModel(context: Context) : BaseViewModel() {

    private val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    fun saveUserData(name: String, phone: String) {
        val editor = sharedPreferences.edit()
        editor.putString("user_name", name)
        editor.putString("user_phone", phone)
        editor.apply()
    }

}