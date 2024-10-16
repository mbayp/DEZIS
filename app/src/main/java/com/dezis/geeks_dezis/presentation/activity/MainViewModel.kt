package com.dezis.geeks_dezis.presentation.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val networkLiveData = NetworkLiveData(application)
}
