package com.dezis.geeks_dezis.data.remote.apiservice

import com.dezis.geeks_dezis.data.remote.model.LoginRequest
import com.dezis.geeks_dezis.data.remote.model.LoginResponse
import com.dezis.geeks_dezis.data.remote.model.ManagerResponse
import com.dezis.geeks_dezis.data.remote.model.MangerRequest
import com.dezis.geeks_dezis.data.remote.model.RefreshTokenRequest
import com.dezis.geeks_dezis.data.remote.model.RegistrationResponse
import com.dezis.geeks_dezis.data.remote.model.TokenResponse
import com.dezis.geeks_dezis.data.remote.model.UserRegisterDto
import com.dezis.geeks_dezis.data.remote.model.VerificationRequest
import com.dezis.geeks_dezis.data.remote.model.VerificationResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {
    @POST("token/refresh/")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): Response<TokenResponse>

    @POST("user/register-user/")
    suspend fun registerUser(@Body userRegisterDto: UserRegisterDto)
    : Response<RegistrationResponse>


    @POST("/user/login-user/")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/user/login-manager/")
    suspend fun loginManager(@Body loginRequest: MangerRequest): Response<ManagerResponse>


    @POST("/user/verify-user/")
    suspend fun verifyCode(@Body request: VerificationRequest): Response<VerificationResponse>

}
