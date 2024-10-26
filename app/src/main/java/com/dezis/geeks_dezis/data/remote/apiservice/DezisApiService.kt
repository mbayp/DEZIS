package com.dezis.geeks_dezis.data.remote.apiservice

import com.dezis.geeks_dezis.data.remote.model.Booking
import com.dezis.geeks_dezis.data.remote.model.BookingRequest
import com.dezis.geeks_dezis.data.remote.model.BookingResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import com.dezis.geeks_dezis.BuildConfig

interface DezisApiService {

    @GET(BuildConfig.get_booking)
    fun getBookings(): Call<List<Booking>>

    @POST(BuildConfig.post_booking)
    fun bookService(
        @Body bookingRequest: BookingRequest
    ): Call<BookingResponse>

}
