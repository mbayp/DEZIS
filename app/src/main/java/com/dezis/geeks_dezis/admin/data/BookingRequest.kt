package com.dezis.geeks_dezis.admin.data

data class BookingRequest(
    val user: Int,
    val service: String,
    val date: String,
    val time: String
)

data class BookingResponse(
    val user: Int,
    val service: String,
    val date: String,
    val time: String
)
