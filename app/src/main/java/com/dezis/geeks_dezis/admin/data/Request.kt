package com.dezis.geeks_dezis.admin.data

data class Request(
    val id: Int,
    val user: Int,
    val service: String,
    val date: String,
    val time: String,
    val created_at: String,
    val fullName: String, // Добавляем поле для ФИО
    val email: String,    // Добавляем поле для электронной почты
    val phone: String,    // Добавляем поле для телефона
    val address: String,  // Добавляем поле для адреса
    val apartmentNumber: String // Добавляем поле для номера квартиры
)
