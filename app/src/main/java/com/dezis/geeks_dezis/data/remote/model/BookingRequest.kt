package com.dezis.geeks_dezis.data.remote.model

import javax.inject.Inject

data class BookingRequest @Inject constructor(
    val user: Int,
    val service: String,
    val date: String,
    val time: String
)

data class BookingResponse @Inject constructor(
    val user: Int,
    val service: String,
    val date: String,
    val time: String
)
