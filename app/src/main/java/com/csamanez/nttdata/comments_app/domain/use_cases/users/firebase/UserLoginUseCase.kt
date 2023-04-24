package com.csamanez.nttdata.comments_app.domain.use_cases.users.firebase

import com.csamanez.nttdata.comments_app.common.ResourceUser
import com.csamanez.nttdata.comments_app.domain.repository.UserFirebaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserLoginUseCase @Inject constructor(
    private val repository: UserFirebaseRepository
) {
    operator fun invoke(email: String, password: String): Flow<ResourceUser<Boolean>> = flow {
        try {
            emit(ResourceUser.Loading<Boolean>(true))
            val statusUserLogin = repository.loginUser(email, password)
            emit(ResourceUser.Success<Boolean>(statusUserLogin))
            if (!statusUserLogin) {
                emit(
                    ResourceUser.Error<Boolean>(
                        "The password is invalid or the user does not have a password"
                    )
                )
            }


        } catch (e: HttpException) {
            emit(
                ResourceUser.Error<Boolean>(
                    e.localizedMessage ?: "An unexpected error occurred!"
                )
            )
        } catch (e: IOException) {
            emit(
                ResourceUser.Error<Boolean>(
                    "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }

}