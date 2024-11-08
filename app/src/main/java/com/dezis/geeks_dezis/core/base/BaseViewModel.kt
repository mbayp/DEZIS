package com.dezis.geeks_dezis.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dezis.geeks_dezis.core.common.Either
import com.dezis.geeks_dezis.core.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    protected fun <T> mutableStateFlow() = MutableStateFlow<UiState<T>>(UiState.Loading())

    protected fun <T, S> gatherRequest(
        flow: Flow<Either<String, T>>,
        state: MutableStateFlow<UiState<S>>,
        mappedData: (data: T) -> S,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UiState.Loading()
            flow.collect {
                when (it) {
                    is Either.Left -> {
                        state.value = UiState.Error(it.value)
                    }
                    is Either.Right -> {
                        state.value = UiState.Success(mappedData(it.value))
                    }
                }
            }
        }
    }

}