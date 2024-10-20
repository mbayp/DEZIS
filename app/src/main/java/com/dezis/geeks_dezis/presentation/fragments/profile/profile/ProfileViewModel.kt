package com.dezis.geeks_dezis.presentation.fragments.profile.profile

import android.content.Context
import com.dezis.geeks_dezis.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val context: Context) : BaseViewModel() {

    private val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    fun saveUserData(name: String, phone: String) {
        val editor = sharedPreferences.edit()
        editor.putString("user_name", name)
        editor.putString("user_phone", phone)
        editor.apply()
    }
}
