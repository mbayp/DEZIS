package com.dezis.geeks_dezis.data.remote.model

data class RefreshTokenRequest(
    val refresh: String
)

data class TokenResponse(
    val access: String,
    val refresh: String
)
