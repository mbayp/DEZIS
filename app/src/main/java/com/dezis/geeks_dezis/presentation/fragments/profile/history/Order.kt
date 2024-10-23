package com.dezis.geeks_dezis.presentation.fragments.profile.history

import javax.inject.Inject

data class FirstTreatment @Inject constructor(
    val clientName: String,
    val service: String,
    val address: String,
    val houseNumber: String,
    val date: String,
    val time: String
)

data class Treatment @Inject constructor(
    val service: String,
    val address: String,
    val date: String,
    val time: String
)
