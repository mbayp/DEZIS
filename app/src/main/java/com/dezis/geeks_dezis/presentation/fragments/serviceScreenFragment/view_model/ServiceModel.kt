package com.dezis.geeks_dezis.presentation.fragments.serviceScreenFragment.view_model

import javax.inject.Inject

data class ServiceModel @Inject constructor(
    val imageResId: Int,
    val title: String,
    val description: String
)
