package com.csamanez.nttdata.comments_app.data.repository

import com.csamanez.nttdata.comments_app.domain.repository.UserFirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class UserFirebaseRepositoryImpl @Inject constructor(
    private val authApi: FirebaseAuth
) : UserFirebaseRepository {

    override suspend fun registerUser(email: String, password: String): Boolean {
        var isSuccessfulRegister = false

        authApi.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                isSuccessfulRegister = task.isSuccessful

//                if (!isSuccessfulRegister) {
//                    val exception = task.exception
//                    if (exception is FirebaseException)
//                        Log.d("ExceptionRepository", exception.message.toString())
//                }
            }
        return isSuccessfulRegister
    }

    override suspend fun loginUser(email: String, password: String): Boolean {
        var isSuccessfulLogin = false

        authApi.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                isSuccessfulLogin = task.isSuccessful

//                if (!isSuccessfulLogin) {
//                    val exception = task.exception
//                    if (exception is FirebaseException)
//                        Log.d("ExceptionRepository", exception.message.toString())
//                }
            }
        return isSuccessfulLogin
    }

}
