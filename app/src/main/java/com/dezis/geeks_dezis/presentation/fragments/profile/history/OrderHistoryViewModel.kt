package com.dezis.geeks_dezis.presentation.fragments.profile.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dezis.geeks_dezis.core.base.makeNetworkRequest
import com.dezis.geeks_dezis.core.common.Either
import com.dezis.geeks_dezis.core.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderHistoryViewModel : ViewModel() {

    private val _firstTreatmentState = MutableStateFlow<UiState<FirstTreatment>>(UiState.Idle())
    val firstTreatmentState: StateFlow<UiState<FirstTreatment>> get() = _firstTreatmentState

    private val _allTreatmentsState = MutableStateFlow<UiState<List<Treatment>>>(UiState.Idle())
    val allTreatmentsState: StateFlow<UiState<List<Treatment>>> get() = _allTreatmentsState

    init {
        loadFirstTreatment()
        loadAllTreatments()
    }

    private fun loadFirstTreatment() {
        val firstTreatmentFlow: Flow<Either<String, FirstTreatment>> = makeNetworkRequest(
            request = {
                FirstTreatment(
                    "Alexey Ivanovich",
                    "Дезинфекция",
                    "Восток-5",
                    "13/21",
                    "17.11.2024",
                    "21:30"
                )
            }
        )

        viewModelScope.launch {
            firstTreatmentFlow.collect { either ->
                when (either) {
                    is Either.Right -> {
                        _firstTreatmentState.value = UiState.Success(either.value)
                    }

                    is Either.Left -> {
                        _firstTreatmentState.value = UiState.Error(either.value)
                    }
                }
            }
        }
    }

    private fun loadAllTreatments() {
        val allTreatmentsFlow: Flow<Either<String, List<Treatment>>> = makeNetworkRequest(
            request = {
                listOf(
                    Treatment("Дезинфекция", "Восток-5", "17.11.2024", "21:30"),
                    Treatment("Фумигация", "Восток-3", "20.12.2024", "16:00")
                )
            }
        )

        viewModelScope.launch {
            allTreatmentsFlow.collect { either ->
                when (either) {
                    is Either.Right -> {
                        _allTreatmentsState.value = UiState.Success(either.value)
                    }

                    is Either.Left -> {
                        _allTreatmentsState.value = UiState.Error(either.value)
                    }
                }
            }
        }
    }
}