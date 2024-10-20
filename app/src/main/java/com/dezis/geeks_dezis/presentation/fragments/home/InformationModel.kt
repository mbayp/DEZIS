package com.dezis.geeks_dezis.presentation.fragments.home

import javax.inject.Inject

data class InformationModel @Inject constructor(
    val id: Int = 0,
    val description: String
)
