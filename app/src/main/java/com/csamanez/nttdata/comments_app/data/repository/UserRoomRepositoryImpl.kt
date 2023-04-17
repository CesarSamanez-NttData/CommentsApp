package com.csamanez.nttdata.comments_app.data.repository

import com.csamanez.nttdata.comments_app.data.remote.room.UserDao
import com.csamanez.nttdata.comments_app.domain.model.User
import com.csamanez.nttdata.comments_app.domain.repository.UserRoomRepository
import kotlinx.coroutines.flow.Flow

class UserRoomRepositoryImpl(
    private val dao: UserDao
) : UserRoomRepository {

    override fun getUsers(): Flow<List<User>> {
        return dao.getUsers()
    }

    override suspend fun getUser(email: String, password: String): User? {
        return dao.getUser(email, password)
    }

    override suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user)
    }

}