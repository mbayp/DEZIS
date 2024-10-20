package com.dezis.geeks_dezis.data.remote.model


data class ChatSendMessageModel(

    val chat: Int,
    val `file`: String,
    val image: String,
    val is_read: Boolean,
    val sender: Int,
    val text: String
)