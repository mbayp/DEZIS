package com.dezis.geeks_dezis.data.repositories

import com.dezis.geeks_dezis.data.remote.apiservice.UserApiService
import com.dezis.geeks_dezis.data.remote.model.RefreshTokenRequest
import com.dezis.geeks_dezis.data.remote.model.RegistrationResponse
import com.dezis.geeks_dezis.data.remote.model.TokenResponse
import com.dezis.geeks_dezis.data.remote.model.UserRegisterDto
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



