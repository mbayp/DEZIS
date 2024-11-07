package com.dezis.geeks_dezis.presentation.fragments.admin_request

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dezis.geeks_dezis.core.base.BaseViewModel
import com.dezis.geeks_dezis.core.common.UiState
import com.dezis.geeks_dezis.data.remote.model.inactiveUser.InactiveUserModel
import com.dezis.geeks_dezis.data.repositories.InactiveUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestViewModell @Inject constructor(private val repository: InactiveUserRepository) :
    BaseViewModel() {


    fun getInactiveUser() = repository.getInactiveUser()

//    private val _inactiveUser = MutableStateFlow<UiState<InactiveUserModel>>(UiState.Error("daw"))
//    val inactiveUser: StateFlow<UiState<InactiveUserModel>> get() = _inactiveUser
//
//    fun getInactiveUser(){
//        viewModelScope.launch {
//            Log.e("ololo", "getInactiveUser: 12345", )
//            try {
//                repository.getInactiveUsers().collect { data ->
//                    Log.e("ololo", "g11111r: $data")
//                    _inactiveUser.value = UiState.Success(data)
//                }
//            } catch (e: Exception) {
//                _inactiveUser.value = UiState.Error(e.message ?: "Unknown error occurred")
//            }
//        }
//    }


}