package com.dezis.geeks_dezis.presentation.fragments.home

import javax.inject.Inject

data class ServiceModel @Inject constructor(
    val service: String,
    val id: Int = 0,
    val info: String,
    val btn: String
)
