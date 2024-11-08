package com.dezis.geeks_dezis.data.repositories

import com.dezis.geeks_dezis.data.remote.apiservice.UserApiService
import com.dezis.geeks_dezis.data.remote.model.register.RefreshTokenRequest
import com.dezis.geeks_dezis.data.remote.model.register.RegistrationResponse
import com.dezis.geeks_dezis.data.remote.model.register.TokenResponse
import com.dezis.geeks_dezis.data.remote.model.user.UserRegisterDto
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: UserApiService) {

    suspend fun refreshTokens(refreshToken: String): TokenResponse? {
        val request = RefreshTokenRequest(refreshToken)
        val response = apiService.refreshToken(request)
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun registerUser(userRegistrationRequest: UserRegisterDto): Response<RegistrationResponse> {
        return apiService.registerUser(userRegistrationRequest)
    }

}