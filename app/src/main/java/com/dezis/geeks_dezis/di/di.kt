package com.dezis.geeks_dezis.di

//class di {
//}
import com.dezis.geeks_dezis.data.remote.apiservice.ChatService
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)  // Модуль будет жить в пределах всего приложения
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideChatService(okHttpClient: OkHttpClient): ChatService {
        return Scarlet.Builder()
            .webSocketFactory(okHttpClient.newWebSocketFactory("wss://example.com/api/v1/chat/messages/send"))
            .addStreamAdapterFactory(RxJava2StreamAdapterFactory())
            .build()
            .create(ChatService::class.java)

    }
}