package com.csamanez.nttdata.comments_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.csamanez.nttdata.comments_app.presentation.comments.CommentListScreen
import com.csamanez.nttdata.comments_app.presentation.ui.theme.CommentsAppTheme
import com.csamanez.nttdata.comments_app.presentation.users.user_login.UserLoginScreen
import com.csamanez.nttdata.comments_app.presentation.users.user_register.RegisterScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CommentsAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.UserLoginScreen.route
                    ) {

                        composable(
                            route = Screen.UserLoginScreen.route
                        ) {
                            UserLoginScreen(navController)
                        }

                        composable(
                            route = Screen.UserRegisterScreen.route
                        ) {
                            RegisterScreen(navController)
                        }

                        composable(
                            route = Screen.CommentListScreen.route +
                                    "?email={email}&password={password}",
                            arguments = listOf(
                                navArgument(
                                    name = "email"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument(
                                    name = "password"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                },
                            )
                        ) {
                            CommentListScreen(navController)
                        }

                    }
                }
            }
        }
    }
}
