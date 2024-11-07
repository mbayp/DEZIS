package com.dezis.geeks_dezis.data.remote.model.inactiveUser
import com.google.gson.annotations.SerializedName

data class InactiveUserModelItem(
    @SerializedName("address")
    val address: String,
    @SerializedName("apartment_number")
    val apartmentNumber: String,
    @SerializedName("avatar")
    val avatar: Any,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("number")
    val number: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)