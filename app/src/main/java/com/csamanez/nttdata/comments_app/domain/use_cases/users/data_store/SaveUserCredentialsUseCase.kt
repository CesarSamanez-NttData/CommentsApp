package com.csamanez.nttdata.comments_app.domain.use_cases.users.data_store

import com.csamanez.nttdata.comments_app.domain.repository.UserDataStoreRepository
import javax.inject.Inject

class SaveUserCredentialsUseCase @Inject constructor(
    private val repository: UserDataStoreRepository
) {

    suspend operator fun invoke(email: String) {
        repository.saveUserCredentials(email)
    }

}
