package com.dezis.geeks_dezis.data.remote.apiservice

import com.dezis.geeks_dezis.core.common.Constants.DELETE_USER
import com.dezis.geeks_dezis.core.common.Constants.GET_BOOKING
import com.dezis.geeks_dezis.core.common.Constants.GET_INACTIVE_USER
import com.dezis.geeks_dezis.core.common.Constants.GET_USER
import com.dezis.geeks_dezis.core.common.Constants.PATCH_USER_PUT
import com.dezis.geeks_dezis.core.common.Constants.POST_BOOKING
import com.dezis.geeks_dezis.core.common.UiState
import com.dezis.geeks_dezis.data.remote.model.booking.Booking
import com.dezis.geeks_dezis.data.remote.model.booking.BookingRequest
import com.dezis.geeks_dezis.data.remote.model.booking.BookingResponse
import com.dezis.geeks_dezis.data.remote.model.inactiveUser.InactiveUserModel
import com.dezis.geeks_dezis.data.remote.model.inactiveUser.InactiveUserModelItem
import com.dezis.geeks_dezis.data.remote.model.updateUserRequestModel.UpdateUserRequestModel
import com.dezis.geeks_dezis.data.remote.model.user.UserResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface DezisApiService {

    @GET(GET_BOOKING)
    fun getBookings(): Call<List<Booking>>

    @POST(POST_BOOKING)
    fun bookService(
        @Body bookingRequest: BookingRequest
    ): Call<BookingResponse>

    @GET(GET_USER)
    fun getUserData(@Path("id") userId: Int): UserResponse

    @GET(GET_INACTIVE_USER)
    suspend fun getInactiveUsers(): Response<InactiveUserModel>

    @DELETE(DELETE_USER)
    suspend fun deleteUser(@Path("id") userId: Int): Response<Unit>

    @PATCH(PATCH_USER_PUT)
    suspend fun updateUser(@Path("id") userId: Int, @Body user: UpdateUserRequestModel): Response<Unit>

}