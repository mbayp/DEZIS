package com.dezis.geeks_dezis.data.repositories

import com.dezis.geeks_dezis.data.remote.apiservice.DezisApiService
import com.dezis.geeks_dezis.data.remote.model.inactiveUser.InactiveUserModel
import com.dezis.geeks_dezis.data.remote.model.updateUserRequestModel.UpdateUserRequestModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class InactiveUserRepository @Inject constructor(private val api: DezisApiService) {

    fun getInactiveUser(): Flow<Response<InactiveUserModel>> {
        return flow {
            val response = api.getInactiveUsers()
            emit(response)
        }
    }

    suspend fun updateUser(id: Int): Response<Unit> {
        val request = UpdateUserRequestModel(isActive = true)
        return api.updateUser(id, request)
    }

    suspend fun deleteUser(id: Int): Response<Unit> {
        return api.deleteUser(id)
    }
}
