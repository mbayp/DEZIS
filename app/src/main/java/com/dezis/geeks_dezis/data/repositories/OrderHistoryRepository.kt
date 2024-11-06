package com.dezis.geeks_dezis.data.repositories

import com.dezis.geeks_dezis.core.base.BaseRepository
import com.dezis.geeks_dezis.data.remote.apiservice.UserApiService
import javax.inject.Inject

class OrderHistoryRepository@Inject constructor(private val apiService: UserApiService) : BaseRepository()