package com.dezis.geeks_dezis.data.remote.apiservice

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatModel(
    val message: String,
    val fromUserId: String,
): Parcelable