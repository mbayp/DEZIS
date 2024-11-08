package com.dezis.geeks_dezis.data.remote.model.order

data class Order(
    val clientName: String,
    val service: String,
    val address: String,
    val houseNumber: String,
    val date: String,
    val time: String
)