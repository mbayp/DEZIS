package com.dezis.geeks_dezis.presentation.fragments.profile

import androidx.lifecycle.viewModelScope
import com.dezis.geeks_dezis.core.base.BaseViewModel
import com.dezis.geeks_dezis.core.common.UiState
import com.dezis.geeks_dezis.data.remote.apiservice.DezisApiService
import com.dezis.geeks_dezis.data.remote.model.user.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val apiService: DezisApiService
) : BaseViewModel() {

    private val _phoneNumber = MutableStateFlow<UiState<String>>(UiState.Loading())
    val phoneNumber: StateFlow<UiState<String>> get() = _phoneNumber

    private val _avatar = MutableStateFlow<UiState<String>>(UiState.Loading())
    val avatar: StateFlow<UiState<String>> get() = _avatar

    fun updatePhoneNumber(newPhoneNumber: String) {
        _phoneNumber.value = UiState.Loading()
        _phoneNumber.value = UiState.Success(newPhoneNumber)
    }

    fun updateAvatar(newAvatarUri: String) {
        _avatar.value = UiState.Loading()
        _avatar.value = UiState.Success(newAvatarUri)
    }

    private val _userData = MutableStateFlow<UiState<UserResponse>>(UiState.Loading())
    val userData: StateFlow<UiState<UserResponse>> get() = _userData

    fun fetchUserData(userId: Int) {
        viewModelScope.launch {
            _userData.value = UiState.Loading()
            try {
                val response = apiService.getUserData(userId)
                _userData.value = UiState.Success(response)
            } catch (e: Exception) {
                _userData.value = UiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

}