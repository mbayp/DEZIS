package com.dezis.geeks_dezis.data.remote.model

import javax.inject.Inject

data class Booking @Inject constructor(
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
