package com.dezis.geeks_dezis.admin.presentation.fragment

data class Order(
    val clientName: String,
    val service: String,
    val address: String,
    val date: String,
    val isCompleted: Boolean
)