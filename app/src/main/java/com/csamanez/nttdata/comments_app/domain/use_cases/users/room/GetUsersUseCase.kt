package com.csamanez.nttdata.comments_app.domain.use_cases.users.room

import com.csamanez.nttdata.comments_app.domain.model.User
import com.csamanez.nttdata.comments_app.domain.repository.UserRoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRoomRepository
) {

    operator fun invoke(): Flow<List<User>> {
        return repository.getUsers()
    }

}