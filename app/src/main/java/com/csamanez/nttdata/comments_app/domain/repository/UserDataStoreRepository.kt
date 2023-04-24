package com.csamanez.nttdata.comments_app.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserDataStoreRepository {

    suspend fun getUserCredentials(): Flow<String>

    suspend fun saveUserCredentials(email: String)

}