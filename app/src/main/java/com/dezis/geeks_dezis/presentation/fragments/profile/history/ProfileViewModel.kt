package com.dezis.geeks_dezis.presentation.fragments.profile.history

import com.dezis.geeks_dezis.core.base.BaseViewModel
import com.dezis.geeks_dezis.core.common.UiState
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : BaseViewModel() {
    private val _phoneNumber = mutableStateFlow<String>()
    val phoneNumber: StateFlow<UiState<String>> get() = _phoneNumber

    private val _avatar = mutableStateFlow<String>()
    val avatar: StateFlow<UiState<String>> get() = _avatar

    fun updatePhoneNumber(newPhoneNumber: String) {
        _phoneNumber.value = UiState.Loading()
        _phoneNumber.value = UiState.Success(newPhoneNumber)
    }

    fun updateAvatar(newAvatarUri: String) {
        _avatar.value = UiState.Loading()
        _avatar.value = UiState.Success(newAvatarUri)
    }
}