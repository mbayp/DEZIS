package com.dezis.geeks_dezis.presentation.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    val networkLiveData = NetworkLiveData(application)
    private val _navigateToError = MutableLiveData<Boolean>()
    val navigateToError: LiveData<Boolean> get() = _navigateToError

    fun navigateToErrorFragment() {
        _navigateToError.value = true
    }

    fun resetNavigationState() {
        _navigateToError.value = false
    }
}
