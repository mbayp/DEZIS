package com.dezis.geeks_dezis.di

import com.dezis.geeks_dezis.data.remote.apiservice.DezisApiService
import com.dezis.geeks_dezis.data.remote.apiservice.UserApiService
import com.dezis.geeks_dezis.data.repositories.InactiveUserRepository
import com.dezis.geeks_dezis.data.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserRepository(apiService: UserApiService): UserRepository {
        return UserRepository(apiService)
    }

    @Provides
    fun provideInactiveUserRepository(apiService: DezisApiService): InactiveUserRepository {
        return InactiveUserRepository(apiService)
    }

}