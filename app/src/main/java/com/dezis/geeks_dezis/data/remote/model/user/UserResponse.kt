package com.dezis.geeks_dezis.data.remote.model.user


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_active")
    val isActive: Boolean?,
    @SerializedName("number")
    val number: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("username")
    val username: String?
)