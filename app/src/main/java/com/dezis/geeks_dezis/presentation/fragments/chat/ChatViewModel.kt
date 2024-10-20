/*
package com.dezis.geeks_dezis.presentation.fragments.chat

import android.content.ContentValues.TAG
import android.util.Log
import com.dezis.geeks_dezis.core.base.BaseViewModel
import com.dezis.geeks_dezis.data.remote.apiservice.ChatService
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.OkHttpClient
import javax.inject.Inject

@HiltViewModel
class ChatViewModel@Inject constructor(
    private val okHttpClient: OkHttpClient,  // Inject OkHttpClient или другие зависимости
    private val chatService: ChatService) : BaseViewModel() {

    private var chatService1: ChatService = Scarlet.Builder()
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
}*/
