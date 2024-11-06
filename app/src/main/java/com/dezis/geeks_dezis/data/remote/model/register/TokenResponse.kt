package com.dezis.geeks_dezis.data.remote.model.register

data class TokenResponse(
    val access: String,
    val refresh: String
)