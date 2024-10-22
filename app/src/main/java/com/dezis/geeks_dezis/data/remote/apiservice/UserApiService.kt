package com.dezis.geeks_dezis.data.remote.apiservice

import com.dezis.geeks_dezis.data.remote.model.RefreshTokenRequest
import com.dezis.geeks_dezis.data.remote.model.RegistrationResponse
import com.dezis.geeks_dezis.data.remote.model.TokenResponse
import com.dezis.geeks_dezis.data.remote.model.UserRegisterDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {
    @POST("token/refresh/")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): Response<TokenResponse>


        @POST("user/register-user/")
        suspend fun registerUser(@Body userRegisterDto: UserRegisterDto): Response<RegistrationResponse>

}
