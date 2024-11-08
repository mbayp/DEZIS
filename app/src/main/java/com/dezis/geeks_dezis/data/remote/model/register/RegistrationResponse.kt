package com.dezis.geeks_dezis.data.remote.model.register

data class RegistrationResponse(
    val id: Int,
    val username: String,
    val email: String,
    val apartmentNumber: String,
    val address: String,
    val number:String,
    val otp: String
)
