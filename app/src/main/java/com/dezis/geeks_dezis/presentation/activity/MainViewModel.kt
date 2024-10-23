package com.dezis.geeks_dezis.presentation.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    val networkLiveData = NetworkLiveData(application)
}
