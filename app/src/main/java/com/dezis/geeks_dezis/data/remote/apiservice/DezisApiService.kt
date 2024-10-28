package com.dezis.geeks_dezis.data.remote.apiservice

import com.dezis.geeks_dezis.data.remote.model.Booking
import com.dezis.geeks_dezis.data.remote.model.BookingRequest
import com.dezis.geeks_dezis.data.remote.model.BookingResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import com.dezis.geeks_dezis.BuildConfig
import com.dezis.geeks_dezis.core.common.Constants.get_booking
import com.dezis.geeks_dezis.core.common.Constants.post_booking

interface DezisApiService {

    @GET(get_booking)
    fun getBookings(): Call<List<Booking>>

    @POST(post_booking)
    fun bookService(
        @Body bookingRequest: BookingRequest
    ): Call<BookingResponse>

}
