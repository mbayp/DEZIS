package com.dezis.geeks_dezis.data.repositories

import com.dezis.geeks_dezis.data.remote.apiservice.DezisApiService
import com.dezis.geeks_dezis.data.remote.model.inactiveUser.InactiveUserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class InactiveUserRepository @Inject constructor(private val api: DezisApiService) {

//    suspend fun getInactiveUsers(): Flow<InactiveUserModel> = flow {
//        val response = api.getInactiveUsers()
//        if (response.isSuccessful && response.body() != null) {
//            emit(response.body()!!)
//            Log.e("ololo", "getInactiveUsers: ${response.body()}", )
//        } else {
//            throw Exception("Error: ${response.message()}")
//        }
//    }.flowOn(Dispatchers.IO)


    fun getInactiveUser(): Flow<Response<InactiveUserModel>> {
        return flow {
            // Выполнение сетевого запроса в корутине
            val response = api.getInactiveUsers()
            emit(response)  // Отправляем ответ в поток
        }

    }
}
