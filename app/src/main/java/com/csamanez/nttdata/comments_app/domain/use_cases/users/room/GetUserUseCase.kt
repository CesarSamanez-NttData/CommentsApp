package com.csamanez.nttdata.comments_app.domain.use_cases.users.room

import com.csamanez.nttdata.comments_app.domain.model.User
import com.csamanez.nttdata.comments_app.domain.repository.UserRoomRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRoomRepository
) {

    suspend operator fun invoke(email: String, password: String): User? {
        return repository.getUser(email, password)
    }

}