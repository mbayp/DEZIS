package com.dezis.geeks_dezis.presentation.fragments.profile


import com.google.gson.annotations.SerializedName

data class UserResponse(
//    @SerializedName("avatar")
    val avatar: String?,
//    @SerializedName("email")
    val email: String?,
//    @SerializedName("id")
    val id: Int?,
//    @SerializedName("number")
    val number: String?,
//    @SerializedName("password")
    val password: String?,
//    @SerializedName("username")
    val username: String?
)