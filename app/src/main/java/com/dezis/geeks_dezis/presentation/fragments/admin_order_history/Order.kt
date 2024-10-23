package com.dezis.geeks_dezis.presentation.fragments.admin_order_history

import javax.inject.Inject

data class Order @Inject constructor(
    val clientName: String,
    val service: String,
    val address: String,
    val date: String,
    val isCompleted: Boolean
)
