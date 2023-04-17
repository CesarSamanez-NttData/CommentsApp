package com.csamanez.nttdata.comments_app.domain.repository

interface UserFirebaseRepository {

    suspend fun registerUser(email: String, password: String): Boolean

    suspend fun loginUser(email: String, password: String): Boolean

}