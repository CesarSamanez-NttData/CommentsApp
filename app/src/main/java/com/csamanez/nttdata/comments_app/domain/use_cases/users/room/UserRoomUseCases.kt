package com.csamanez.nttdata.comments_app.domain.use_cases.users.room

data class UserRoomUseCases(
    val getUser: GetUserUseCase,
    val getUsers: GetUsersUseCase,
    val addUser: AddUserUseCase,
    val deleteUser: DeleteUserUseCase
)
