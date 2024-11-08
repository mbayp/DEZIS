package com.dezis.geeks_dezis.data.remote.interceptors

import com.dezis.geeks_dezis.presentation.activity.MainViewModel
import javax.inject.Inject

class ErrorHandler @Inject constructor() {

    private var mainViewModel: MainViewModel? = null

    fun setMainViewModel(viewModel: MainViewModel) {
        this.mainViewModel = viewModel
    }
    fun handleError(code: Int) {
        mainViewModel?.let {
            if (code == 500) {
                it.navigateToErrorFragment()
            }
        }
    }
}
