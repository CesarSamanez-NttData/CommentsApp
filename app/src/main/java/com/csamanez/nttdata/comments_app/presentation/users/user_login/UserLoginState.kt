package com.csamanez.nttdata.comments_app.presentation.users.user_login

data class UserLoginState(
    var isLoading: Boolean = false,
    var isSuccess: Boolean = false,
    var error: String = ""
)