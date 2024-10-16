package com.dezis.geeks_dezis.api.apis


import com.dezis.geeks_dezis.admin.data.Request
import retrofit2.Call
import retrofit2.http.GET

interface BookApiService {
    @GET("api/v1/contact/booking/")
    fun getRequests(): Call<List<Request>>
}