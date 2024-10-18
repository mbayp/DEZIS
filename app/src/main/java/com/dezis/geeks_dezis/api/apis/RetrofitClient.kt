package com.dezis.geeks_dezis.api.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://209.38.228.54:8084/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val bookApiService: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }
}
