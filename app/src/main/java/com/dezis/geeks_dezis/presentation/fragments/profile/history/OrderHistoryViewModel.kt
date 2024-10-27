package com.dezis.geeks_dezis.presentation.fragments.profile.history

import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dezis.geeks_dezis.core.base.makeNetworkRequest
import com.dezis.geeks_dezis.core.common.Either
import com.dezis.geeks_dezis.core.common.UiState
import com.dezis.geeks_dezis.databinding.FragmentOrderHistoryBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class OrderHistoryViewModel @Inject constructor() : ViewModel() {

    private val _firstTreatmentState = MutableStateFlow<UiState<FirstTreatment>>(UiState.Idle())
    val firstTreatmentState: StateFlow<UiState<FirstTreatment>> get() = _firstTreatmentState

    private val _allTreatmentsState = MutableStateFlow<UiState<List<Treatment>>>(UiState.Idle())
    val allTreatmentsState: StateFlow<UiState<List<Treatment>>> get() = _allTreatmentsState

    init {
        loadFirstTreatment()
        loadAllTreatments()
    }

    private fun loadFirstTreatment() {
        val firstTreatmentFlow: Flow<Either<String, FirstTreatment>> = makeNetworkRequest {
            FirstTreatment("Alexey Ivanovich", "Дезинфекция", "Восток-5", "13/21", "17.11.2024", "21:30")
        }

        viewModelScope.launch {
            firstTreatmentFlow.collect { either ->
                _firstTreatmentState.value = when (either) {
                    is Either.Right -> UiState.Success(either.value)
                    is Either.Left -> UiState.Error(either.value)
                }
            }
        }
    }

    private fun loadAllTreatments() {
        val allTreatmentsFlow: Flow<Either<String, List<Treatment>>> = makeNetworkRequest {
            listOf(
                Treatment("Дезинфекция", "Восток-5", "17.11.2024", "21:30"),
                Treatment("Фумигация", "Восток-3", "20.12.2024", "16:00")
            )
        }

        viewModelScope.launch {
            allTreatmentsFlow.collect { either ->
                _allTreatmentsState.value = when (either) {
                    is Either.Right -> UiState.Success(either.value)
                    is Either.Left -> UiState.Error(either.value)
                }
            }
        }
    }
}
