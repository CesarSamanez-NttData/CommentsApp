package com.csamanez.nttdata.comments_app.domain.use_cases.users.data_store

import com.csamanez.nttdata.comments_app.domain.repository.UserDataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserCredentialsUseCase @Inject constructor(
    private val repository: UserDataStoreRepository
) {

    suspend operator fun invoke(): Flow<String> {
        return repository.getUserCredentials()
    }

}