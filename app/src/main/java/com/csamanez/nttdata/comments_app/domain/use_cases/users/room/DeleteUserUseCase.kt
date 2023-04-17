package com.csamanez.nttdata.comments_app.domain.use_cases.users.room

import com.csamanez.nttdata.comments_app.domain.model.User
import com.csamanez.nttdata.comments_app.domain.repository.UserRoomRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val repository: UserRoomRepository
) {

    suspend operator fun invoke(user: User) {
        repository.deleteUser(user)
    }

}