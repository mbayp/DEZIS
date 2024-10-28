package com.dezis.geeks_dezis.data.remote.model

data class VerificationRequest(
    val email: String,
    val otp: String
)

data class VerificationResponse(
    val success: Boolean,
    val message: String
)
