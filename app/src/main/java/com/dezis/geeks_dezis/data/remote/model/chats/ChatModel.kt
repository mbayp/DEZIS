package com.dezis.geeks_dezis.data.remote.model.chats

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatModel(
    val message: String,
    val fromUserId: String,
    val timestamp: String,
    val id: Int,
    ): Parcelable