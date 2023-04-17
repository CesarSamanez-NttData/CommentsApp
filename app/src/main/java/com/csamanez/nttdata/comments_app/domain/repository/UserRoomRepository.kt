package com.csamanez.nttdata.comments_app.domain.repository

import com.csamanez.nttdata.comments_app.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRoomRepository {

    fun getUsers(): Flow<List<User>>

    suspend fun getUser(email: String, password: String): User?

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

}