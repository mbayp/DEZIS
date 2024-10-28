package com.dezis.geeks_dezis.data.remote.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val message: String
)

