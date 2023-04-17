package com.csamanez.nttdata.comments_app.presentation.users.user_register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csamanez.nttdata.comments_app.common.ResourceUser
import com.csamanez.nttdata.comments_app.domain.model.InvalidUserException
import com.csamanez.nttdata.comments_app.domain.model.User
import com.csamanez.nttdata.comments_app.domain.use_cases.users.firebase.UserRegisterUseCase
import com.csamanez.nttdata.comments_app.domain.use_cases.users.room.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRegisterViewModel @Inject constructor(
    private val userRegisterUseCase: UserRegisterUseCase,
    private val addUserUseCase: AddUserUseCase
) : ViewModel() {

    private val _state = mutableStateOf(UserRegisterState())
    val state: State<UserRegisterState> = _state

    fun userRegisterFirebase(email: String, password: String) {
        userRegisterUseCase.invoke(email, password).onEach { result ->
            when (result) {
                is ResourceUser.Loading -> {
                    _state.value = UserRegisterState(isLoading = true)
                }

                is ResourceUser.Success -> {
                    _state.value = UserRegisterState(isSuccess = true)
                }

                is ResourceUser.Error -> {
                    _state.value = UserRegisterState(
                        error = result.message ?: "An unexpected error occurred!."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addUserRoom(user: User) {
        viewModelScope.launch {
            try {
                addUserUseCase(user)
            } catch (e: InvalidUserException) {
                e.localizedMessage
            }
        }
    }

}