package com.dezis.geeks_dezis.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ErrorHandlingInterceptor @Inject constructor(
    private val errorHandler: ErrorHandler
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (!response.isSuccessful) {
            errorHandler.handleError(response.code)
        }
        return response
    }
}
