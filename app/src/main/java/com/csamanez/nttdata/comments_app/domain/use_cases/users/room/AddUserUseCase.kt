package com.csamanez.nttdata.comments_app.domain.use_cases.users.room

import com.csamanez.nttdata.comments_app.domain.model.InvalidUserException
import com.csamanez.nttdata.comments_app.domain.model.User
import com.csamanez.nttdata.comments_app.domain.repository.UserRoomRepository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val repository: UserRoomRepository
) {

    @Throws(InvalidUserException::class)
    suspend operator fun invoke(user: User) {
        if (
            user.name!!.isBlank() ||
            user.lastname!!.isBlank() ||
            user.email!!.isBlank() ||
            user.password!!.isBlank()
        ) {
            throw InvalidUserException("The fields cannot be empty.")
        }

        repository.insertUser(user)
    }
}