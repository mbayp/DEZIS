package com.dezis.geeks_dezis.data.remote.model.verify

data class VerificationRequest(
    val email: String,
    val otp: String
)