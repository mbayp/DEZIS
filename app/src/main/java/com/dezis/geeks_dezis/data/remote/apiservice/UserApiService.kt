package com.dezis.geeks_dezis.data.remote.apiservice

import com.dezis.geeks_dezis.data.remote.model.login.LoginRequest
import com.dezis.geeks_dezis.data.remote.model.login.LoginResponse
import com.dezis.geeks_dezis.data.remote.model.manager.ManagerRequest
import com.dezis.geeks_dezis.data.remote.model.manager.ManagerResponse
import com.dezis.geeks_dezis.data.remote.model.register.RefreshTokenRequest
import com.dezis.geeks_dezis.data.remote.model.register.RegistrationResponse
import com.dezis.geeks_dezis.data.remote.model.register.TokenResponse
import com.dezis.geeks_dezis.data.remote.model.user.UserRegisterDto
import com.dezis.geeks_dezis.data.remote.model.verify.VerificationRequest
import com.dezis.geeks_dezis.data.remote.model.verify.VerificationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {

    @POST("token/refresh/")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): Response<TokenResponse>

    @POST("user/register-user/")
    suspend fun registerUser(@Body userRegisterDto: UserRegisterDto)
            : Response<RegistrationResponse>

    @POST("/api/v1/user/login-user/")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/api/v1/user/login-manager/")
    suspend fun loginManager(@Body loginRequest: ManagerRequest): Response<ManagerResponse>

    @POST("user/verify-user/")
    suspend fun verifyCode(@Body request: VerificationRequest): Response<VerificationResponse>
}