package com.dezis.geeks_dezis.data.remote.apiservice

import com.dezis.geeks_dezis.data.remote.model.Booking
import com.dezis.geeks_dezis.data.remote.model.BookingRequest
import com.dezis.geeks_dezis.data.remote.model.BookingResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import com.dezis.geeks_dezis.core.common.Constants.get_booking
import com.dezis.geeks_dezis.core.common.Constants.get_user
import com.dezis.geeks_dezis.core.common.Constants.post_booking
import com.dezis.geeks_dezis.presentation.fragments.profile.UserResponse
import retrofit2.http.Path

interface DezisApiService {

    @GET(get_booking)
    fun getBookings(): Call<List<Booking>>

    @POST(post_booking)
    fun bookService(
        @Body bookingRequest: BookingRequest
    ): Call<BookingResponse>

    @GET(get_user)
    fun getUserData(@Path("id") userId: Int): UserResponse
}
