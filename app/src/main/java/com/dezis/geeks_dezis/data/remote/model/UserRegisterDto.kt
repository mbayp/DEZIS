package com.dezis.geeks_dezis.data.remote.model

data class UserRegisterDto(
    val username: String,
    val email: String,
    var apartmentNumber: String,
    var address: String,
    val password: String,
    val number:String
)
