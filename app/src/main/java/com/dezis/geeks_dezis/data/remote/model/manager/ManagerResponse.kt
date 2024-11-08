package com.dezis.geeks_dezis.data.remote.model.manager

data class ManagerResponse(
    val accessToken: String,
    val refreshToken: String,
    val message: String
)