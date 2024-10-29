package com.dezis.geeks_dezis.data.remote.model

data class MangerRequest(
    val login: String,
    val password: String
)

data class ManagerResponse(
    val accessToken: String,
    val refreshToken: String,
    val message: String
)

