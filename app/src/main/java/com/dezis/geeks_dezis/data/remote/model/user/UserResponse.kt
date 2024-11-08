package com.dezis.geeks_dezis.data.remote.model.user

data class UserResponse(
    val id: Int,
    val username: String?,
    val email: String?,
    val apartment_number: String?,
    val password: String?,
    val address: String?,
    val number: String?,
    val avatar: String?,
    val is_active: Boolean,
    val created_at: String?
)
