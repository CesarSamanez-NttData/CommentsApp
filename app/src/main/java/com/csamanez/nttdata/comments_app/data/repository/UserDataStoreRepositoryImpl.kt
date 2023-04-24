package com.csamanez.nttdata.comments_app.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.csamanez.nttdata.comments_app.domain.repository.UserDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataStoreRepositoryImpl @Inject constructor(
    private val context: Context
) : UserDataStoreRepository {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "credentials")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }

    override suspend fun getUserCredentials(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[USER_EMAIL_KEY] ?: ""
        }
    }

    override suspend fun saveUserCredentials(email: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = email
        }
    }

}