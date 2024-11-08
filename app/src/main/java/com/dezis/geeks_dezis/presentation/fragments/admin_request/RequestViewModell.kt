package com.dezis.geeks_dezis.presentation.fragments.admin_request

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dezis.geeks_dezis.core.base.BaseViewModel
import com.dezis.geeks_dezis.core.common.UiState
import com.dezis.geeks_dezis.data.remote.model.inactiveUser.InactiveUserModel
import com.dezis.geeks_dezis.data.remote.model.updateUserRequestModel.UpdateUserRequestModel
import com.dezis.geeks_dezis.data.repositories.InactiveUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class RequestViewModell @Inject constructor(private val repository: InactiveUserRepository) :
    BaseViewModel() {

    private val _inactiveUserState = MutableStateFlow<UiState<InactiveUserModel>>(UiState.Loading())
    val inactiveUserState: StateFlow<UiState<InactiveUserModel>> = _inactiveUserState

    init {
        getInactiveUser()
    }

    private fun getInactiveUser() {
        Log.e("ololo", "getInactiveUser: 1233")
        viewModelScope.launch {
            _inactiveUserState.value = UiState.Loading()
            try {
                repository.getInactiveUser().collect { response ->
                    Log.e("ololo", "getInactiveUser: ${response.isSuccessful}")
                    if (response.isSuccessful) {
                        val users = response.body()
                        if (users != null) {
                            _inactiveUserState.value = UiState.Success(users)
                        } else {
                            _inactiveUserState.value = UiState.Error("Пустой список пользователей")
                        }
                    } else {
                        _inactiveUserState.value = UiState.Error("Ошибка загрузки данных")
                    }
                }
            } catch (e: Exception) {
                _inactiveUserState.value = UiState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }

    fun updateUser(id: Int, model: UpdateUserRequestModel) {
        viewModelScope.launch {
            try {
                val response = repository.updateUser(id, model)
                Log.e("ololo", "updateUser failed with code: ${response.code()}, message: ${response.message()}")
                val errorBody = response.errorBody()?.string()
//                Log.e("ololo", "updateUser error body: $errorBody")
                if (response.isSuccessful) {
                    getInactiveUser()
                } else {
                    _inactiveUserState.value = UiState.Error("Ошибка подтверждения пользователя")
                }
            } catch (e: Exception) {
                _inactiveUserState.value =
                    UiState.Error(e.message ?: "Ошибка подтверждения пользователя")
            }
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.deleteUser(id)
                if (response.isSuccessful) {
                    getInactiveUser()
                } else {
                    _inactiveUserState.value = UiState.Error("Ошибка удаления пользователя")
                }
            } catch (e: Exception) {
                _inactiveUserState.value =
                    UiState.Error(e.message ?: "Ошибка удаления пользователя")
            }
        }
    }
}
