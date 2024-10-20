package com.dezis.geeks_dezis.presentation.fragments.profile.history

data class FirstTreatment(
    val clientName: String,
    val service: String,
    val address: String,
    val houseNumber: String,
    val date: String,
    val time: String
)

data class Treatment(
    val service: String,
    val address: String,
    val date: String,
    val time: String
)
