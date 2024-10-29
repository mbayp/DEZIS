package com.dezis.geeks_dezis.presentation.fragments.profile

import android.app.Application
import android.content.Context
import com.dezis.geeks_dezis.core.base.BaseViewModel
import com.dezis.geeks_dezis.core.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class ProfileViewModel @Inject constructor(
    application: Application
) : BaseViewModel() {

    private val _phoneNumber = MutableStateFlow<UiState<String>>(UiState.Idle())
    val phoneNumber: StateFlow<UiState<String>> get() = _phoneNumber

    private val _avatar = MutableStateFlow<UiState<String>>(UiState.Idle())
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
