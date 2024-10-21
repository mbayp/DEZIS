package com.dezis.geeks_dezis.di

import android.app.Application
import android.content.Context
import com.dezis.geeks_dezis.core.common.Constants.BASE_URL
import com.dezis.geeks_dezis.data.remote.apiservice.DezisApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
    ): OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        )
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .callTimeout(60, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun provideLotosApiService(retrofit: Retrofit): DezisApiService {
        val apiService = retrofit.create(DezisApiService::class.java)
        return apiService
    }

    /*    @Provides
      @Singleton
      fun provideChatService(okHttpClient: OkHttpClient): ChatService {
          return Scarlet.Builder()
              .webSocketFactory(okHttpClient.newWebSocketFactory("wss://example.com/api/v1/chat/messages/send"))
              .addStreamAdapterFactory(RxJava2StreamAdapterFactory())
              .build()
              .create(ChatService::class.java)

      }*/

}