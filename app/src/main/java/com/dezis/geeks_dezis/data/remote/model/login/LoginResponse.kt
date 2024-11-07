package com.dezis.geeks_dezis.data.remote.model.login

data class LoginResponse(
    val user_id: Int, 
    val accessToken: String,
    val refreshToken: String,
    val message: String
)
