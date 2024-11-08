package com.dezis.geeks_dezis.data.remote.model.updateUserRequestModel


import com.google.gson.annotations.SerializedName

data class UpdateUserRequestModel(
    @SerializedName("avatar")
    val avatar: String? = null,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("number")
    val number: String? = null
)