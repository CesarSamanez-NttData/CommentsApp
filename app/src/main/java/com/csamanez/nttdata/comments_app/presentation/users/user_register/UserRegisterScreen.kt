package com.csamanez.nttdata.comments_app.presentation.users.user_register

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.csamanez.nttdata.comments_app.domain.model.User
import com.csamanez.nttdata.comments_app.presentation.Screen
import com.csamanez.nttdata.comments_app.presentation.utils.ValidateForm

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: UserRegisterViewModel = hiltViewModel()
) {
    var names by remember { mutableStateOf("") }
    var lastNames by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val state = viewModel.state.value


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Create Account",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(vertical = 32.dp)
                )

            }

            OutlinedTextField(
                value = names,
                onValueChange = { names = it },
                label = { Text("Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_name_and_last_name
                        ),
                        contentDescription = "icon_name"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = lastNames,
                onValueChange = { lastNames = it },
                label = { Text("Last Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_name_and_last_name
                        ),
                        contentDescription = "icon_last_name"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
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

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (ValidateForm.validateRegisterForm(names, lastNames, email, password)) {
                        viewModel.userRegisterFirebase(email, password)
                        viewModel.addUserRoom(
                            User(
                                names,
                                lastNames,
                                email,
                                password
                            )
                        )
                        navController.navigate(Screen.UserLoginScreen.route)
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
                    .height(56.dp)
            ) {
                Text(text = "Create Account")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = {
                    navController.navigate(Screen.UserLoginScreen.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Do you already have an account? Log in")
            }
        }

        if (state.isSuccess) {
            Toast.makeText(
                context,
                "Registration was successful",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

