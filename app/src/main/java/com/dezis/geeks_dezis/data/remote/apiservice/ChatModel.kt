package com.dezis.geeks_dezis.data.remote.apiservice

import javax.inject.Inject

data class ChatModel @Inject constructor(
    val message: String,
    val fromUserId: String
)
