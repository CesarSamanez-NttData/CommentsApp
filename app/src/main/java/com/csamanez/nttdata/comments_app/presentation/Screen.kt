package com.csamanez.nttdata.comments_app.presentation

sealed class Screen(
    val route: String
) {

    object UserLoginScreen : Screen("user_login_screen")

    object UserRegisterScreen : Screen("user_register_screen")

    object CommentListScreen : Screen("comment_list_screen")


}