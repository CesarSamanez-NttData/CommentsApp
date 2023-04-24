package com.csamanez.nttdata.comments_app.presentation.users.user_login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.csamanez.nttdata.comments_app.R
import com.csamanez.nttdata.comments_app.presentation.Screen
import com.csamanez.nttdata.comments_app.presentation.utils.ValidateForm

@Composable
fun UserLoginScreen(
    navController: NavController,
    viewModel: UserLoginViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 24.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.icon_app
                ),
                contentDescription = null,
                modifier = Modifier.size(180.dp)
            )
            Text(
                text = "Login",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_email
                        ),
                        contentDescription = "icon_email"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_password
                        ),
                        contentDescription = "icon_password"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (ValidateForm.validateLoginForm(email, password)) {
                        viewModel.userLoginFirebase(email, password)
                    } else {
                        Toast.makeText(
                            context,
                            "Please, verify that the information provided is valid",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = {
                    navController.navigate(Screen.UserRegisterScreen.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign Up")
            }
        }
    }

    // Redirect and save credentials in dataStore
    if (state.isSuccess && state.error.isEmpty()) {
        Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show()

        // Redirect to home
        navController.navigate(
            Screen.CommentListScreen.route + "?email=${email}&password=${password}"
        )

        // Save credential in DataStore
        viewModel.saveUserCredentialsDataStore(email)
        state.isSuccess = false
    }

    // Show error
    if (!state.error.isNullOrEmpty()) {
        Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        state.error = ""
    }

    // Preload email value from dataStore
    LaunchedEffect(viewModel.emailState.value) {
        email = viewModel.emailState.value
    }

}