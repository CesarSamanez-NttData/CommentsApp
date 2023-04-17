package com.csamanez.nttdata.comments_app.presentation.users.user_register

data class UserRegisterState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
)
