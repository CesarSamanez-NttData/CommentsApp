package com.csamanez.nttdata.comments_app.presentation.comments

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csamanez.nttdata.comments_app.common.ResourceComment
import com.csamanez.nttdata.comments_app.domain.use_cases.comments.GetCommentsUseCase
import com.csamanez.nttdata.comments_app.domain.use_cases.users.room.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentListViewModel @Inject constructor(
    private val getCommentsUseCases: GetCommentsUseCase,
    private val getUserUseCase: GetUserUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CommentListState())
    val state: State<CommentListState> = _state

    var name: String = ""
    var lastname: String = ""


    init {

        savedStateHandle.get<String>("email")?.let { userEmail ->
            savedStateHandle.get<String>("password")?.let { userPassword ->
                viewModelScope.launch {
                    getUserUseCase(
                        email = userEmail,
                        password = userPassword
                    )?.also { user ->
                        name = user.name.toString()
                        lastname = user.lastname.toString()
                    }
                }
            }
        }

        getComments()
    }

    private fun getComments() {
        getCommentsUseCases().onEach { result ->
            when (result) {
                is ResourceComment.Loading -> {
                    _state.value = CommentListState(isLoading = true)
                }

                is ResourceComment.Success -> {
                    _state.value = CommentListState(comments = result.data ?: emptyList())
                }

                is ResourceComment.Error -> {
                    _state.value = CommentListState(
                        error = result.message ?: "An unexpected error occurred!."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getUserCredential(): Pair<String, String> {
        return Pair(name, lastname)
    }

}