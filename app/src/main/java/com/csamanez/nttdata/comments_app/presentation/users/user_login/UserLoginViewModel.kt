package com.csamanez.nttdata.comments_app.presentation.users.user_login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csamanez.nttdata.comments_app.common.ResourceUser
import com.csamanez.nttdata.comments_app.domain.use_cases.users.data_store.GetUserCredentialsUseCase
import com.csamanez.nttdata.comments_app.domain.use_cases.users.data_store.SaveUserCredentialsUseCase
import com.csamanez.nttdata.comments_app.domain.use_cases.users.firebase.UserLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserLoginViewModel @Inject constructor(
    private val userLoginUseCase: UserLoginUseCase,
    private val getUserCredentialsUseCase: GetUserCredentialsUseCase,
    private val saveUserCredentialsUseCase: SaveUserCredentialsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(UserLoginState())
    val state: State<UserLoginState> = _state

    private val _emailState = mutableStateOf("")
    val emailState: State<String> = _emailState

    init {
        // Get credentials from DataStore
        viewModelScope.launch {
            getUserCredentialsUseCase().collect { value ->
                _emailState.value = value
            }
        }
    }

    fun userLoginFirebase(email: String, password: String) {
        userLoginUseCase(email, password).onEach { result ->
            when (result) {

                is ResourceUser.Loading -> {
                    _state.value = UserLoginState(isLoading = true)
                }

                is ResourceUser.Success -> {
                    _state.value = UserLoginState(isSuccess = true)
                }

                is ResourceUser.Error -> {
                    _state.value = UserLoginState(
                        error = result.message ?: "An unexpected error occurred!."
                    )
                }

            }
        }.launchIn(viewModelScope)
    }

    fun saveUserCredentialsDataStore(email: String) {
        viewModelScope.launch {
            saveUserCredentialsUseCase(email)
        }
    }

}