package com.dezis.geeks_dezis.presentation.fragments.chat

import android.content.ContentValues.TAG
import android.util.Log
import com.afollestad.materialdialogs.BuildConfig
import com.dezis.geeks_dezis.core.base.BaseViewModel
import com.dezis.geeks_dezis.data.remote.apiservice.ChatService
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import okhttp3.OkHttpClient

class ChatViewModel : BaseViewModel() {
    private var chatService: ChatService = Scarlet.Builder()
        .webSocketFactory(
            OkHttpClient.Builder().build()
                .newWebSocketFactory(""
                   // "wss://free.blr2.piesocket.com/v3/1?api_key=${BuildConfig.websocketApiKey}&notify_self=1"
                )
        )
        .addStreamAdapterFactory(RxJava2StreamAdapterFactory())
        .build().create<ChatService>()

    init {
        observerConnection()
    }


    private fun observerConnection() {
        Log.d(TAG, "Наблюдение за соединением")
        updateConnectionStatus(ConnectionStatus.CONNECTING)
        chatService.observeConnection().subscribe(
            { response ->
                Log.d(TAG, response.toString())
                onResponseReceived(response)
            },
            { error ->
                error.localizedMessage ? .let { Log.e(TAG, it) }
            })
    }

    private fun onResponseReceived(response: WebSocket.Event) {
        when (response) {
            is WebSocket.Event.OnConnectionOpened<*> ->
                updateConnectionStatus(ConnectionStatus.OPENED)

            is WebSocket.Event.OnConnectionClosed ->
                updateConnectionStatus(ConnectionStatus.CLOSED)

            is WebSocket.Event.OnConnectionClosing ->
                updateConnectionStatus(ConnectionStatus.CLOSING)

            is WebSocket.Event.OnConnectionFailed ->
                updateConnectionStatus(ConnectionStatus.FAILED)

            is WebSocket.Event.OnMessageReceived ->
                handleOnMessageReceived(response.message)
        }
    }
}