package com.dezis.geeks_dezis.admin.data

data class Booking(
    val id: Int,
    val user: Int,
    val service: String,
    val date: String,
    val time: String
)
/*
data class BookingResponse(
    val bookings: List<Booking>
)
*/
