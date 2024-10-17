package com.dezis.geeks_dezis.api.apis

import com.dezis.geeks_dezis.admin.data.Booking
import com.dezis.geeks_dezis.admin.data.BookingResponse
import retrofit2.Call
import retrofit2.http.GET

interface BookApiService {
    @GET("api/v1/contact/booking/")
    fun getBookings(): Call<List<Booking>>
}
