package com.dezis.geeks_dezis.presentation.fragments.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dezis.geeks_dezis.core.base.BaseViewModel
import com.dezis.geeks_dezis.data.remote.apiservice.UserApiService
import com.dezis.geeks_dezis.data.remote.model.user.UserRegisterDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstAuthorizationViewModel @Inject constructor(
    private val userApiService: UserApiService
) : BaseViewModel() {

    fun registerUser(userRegisterDto: UserRegisterDto) {
        viewModelScope.launch {
            try {
                val response = userApiService.registerUser(userRegisterDto)
                if (response.isSuccessful) {
                } else {
                }
            } catch (e: Exception) {
            }
        }
    }
}
