package com.dezis.geeks_dezis.presentation.fragments.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dezis.geeks_dezis.core.base.BaseViewModel
import com.dezis.geeks_dezis.core.common.UiState
import com.dezis.geeks_dezis.data.remote.model.RegistrationResponse
import com.dezis.geeks_dezis.data.remote.model.UserRegisterDto
import com.dezis.geeks_dezis.data.repositories.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirstAuthorizationViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {
}
