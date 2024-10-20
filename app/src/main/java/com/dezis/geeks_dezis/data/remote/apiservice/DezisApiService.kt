package com.dezis.geeks_dezis.data.remote.apiservice

import com.dezis.geeks_dezis.data.remote.model.Booking
import com.dezis.geeks_dezis.data.remote.model.BookingRequest
import com.dezis.geeks_dezis.data.remote.model.BookingResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DezisApiService {

    @GET("api/v1/contact/booking/")
    fun getBookings(
    ): Call<List<Booking>>

    @POST("api/v1/contact/booking/")
    fun bookService(
        @Body bookingRequest: BookingRequest
    ): Call<BookingResponse>

}