package com.csamanez.nttdata.comments_app.data.repository

import android.util.Log
import com.csamanez.nttdata.comments_app.domain.repository.UserFirebaseRepository
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserFirebaseRepositoryImpl @Inject constructor(
    private val authApi: FirebaseAuth
) : UserFirebaseRepository {

    override suspend fun registerUser(email: String, password: String): Boolean {
        var isSuccessfulRegister = false

        authApi.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                isSuccessfulRegister = task.isSuccessful

                if (!isSuccessfulRegister) {
                    val exception = task.exception
                    if (exception is FirebaseException)
                        Log.d("Exception Register", exception.message.toString())
                }
            }.await()

        return isSuccessfulRegister
    }

    override suspend fun loginUser(email: String, password: String): Boolean {
        var isSuccessfulLogin = false

        try {

            authApi.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    isSuccessfulLogin = task.isSuccessful
                }.await()

        } catch (e: Exception) {
            Log.d("Exception Firebase --> ", e.localizedMessage)
            return false
        }

        return isSuccessfulLogin
    }

}
