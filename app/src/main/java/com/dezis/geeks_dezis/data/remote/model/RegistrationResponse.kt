package com.dezis.geeks_dezis.data.remote.model

data class RegistrationResponse(
    val id: Int,
    val username: String,
    val email: String,
    val apartmentNumber: String,
    val address: String
)
