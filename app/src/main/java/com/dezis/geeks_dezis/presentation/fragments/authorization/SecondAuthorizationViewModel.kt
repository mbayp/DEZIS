package com.dezis.geeks_dezis.presentation.fragments.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dezis.geeks_dezis.core.base.BaseViewModel
import com.dezis.geeks_dezis.core.common.UiState
import com.dezis.geeks_dezis.data.remote.model.register.RegistrationResponse
import com.dezis.geeks_dezis.data.remote.model.register.TokenResponse
import com.dezis.geeks_dezis.data.remote.model.user.UserRegisterDto
import com.dezis.geeks_dezis.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondAuthorizationViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private val _refreshTokenResult = MutableLiveData<UiState<TokenResponse>>()
    val refreshTokenResult: LiveData<UiState<TokenResponse>> get() = _refreshTokenResult

    private val _registrationResult = MutableLiveData<UiState<RegistrationResponse>>()
    val registrationResult: LiveData<UiState<RegistrationResponse>> get() = _registrationResult

    fun refreshTokens(refreshToken: String) {
        viewModelScope.launch {
            _refreshTokenResult.value = UiState.Loading()
            val tokenResponse = userRepository.refreshTokens(refreshToken)
            _refreshTokenResult.value = if (tokenResponse != null) {
                UiState.Success(tokenResponse)
            } else {
                UiState.Error("Ошибка обновления токена")
            }
        }
    }

    fun registerUser(userRegisterDto: UserRegisterDto, accessToken: String) {
        viewModelScope.launch {
            _registrationResult.value = UiState.Loading()
            val response = userRepository.registerUser(userRegisterDto)
            _registrationResult.value = if (response.isSuccessful) {
                UiState.Success(response.body()!!)
            } else {
                UiState.Error("Ошибка регистрации: ${response.errorBody()?.string()}")
            }
        }
    }

}