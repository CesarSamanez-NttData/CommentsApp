package com.csamanez.nttdata.comments_app.presentation.users.user_login

data class UserLoginState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
)