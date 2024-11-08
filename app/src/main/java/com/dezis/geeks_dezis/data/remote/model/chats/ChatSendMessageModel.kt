package com.dezis.geeks_dezis.data.remote.model.chats

import com.google.gson.annotations.SerializedName

data class ChatSendMessageModel(
    val chat: Int,
    val file: String,
    val image: String,
    @SerializedName("is_read")
    val isRead: Boolean,
    val sender: Int,
    val text: String,
)
