package com.dezis.geeks_dezis.data.remote.model.booking

data class Booking(
    val id: Int,
    val user: Int,
    val service: String,
    val date: String,
    val time: String,
)