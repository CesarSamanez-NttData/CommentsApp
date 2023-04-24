package com.csamanez.nttdata.comments_app.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.csamanez.nttdata.comments_app.common.Constants
import com.csamanez.nttdata.comments_app.data.remote.JSONPlaceholderApi
import com.csamanez.nttdata.comments_app.data.remote.room.UserDatabase
import com.csamanez.nttdata.comments_app.data.repository.CommentRepositoryImpl
import com.csamanez.nttdata.comments_app.data.repository.UserDataStoreRepositoryImpl
import com.csamanez.nttdata.comments_app.data.repository.UserFirebaseRepositoryImpl
import com.csamanez.nttdata.comments_app.data.repository.UserRoomRepositoryImpl
import com.csamanez.nttdata.comments_app.domain.repository.CommentRepository
import com.csamanez.nttdata.comments_app.domain.repository.UserDataStoreRepository
import com.csamanez.nttdata.comments_app.domain.repository.UserFirebaseRepository
import com.csamanez.nttdata.comments_app.domain.repository.UserRoomRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

    // JSONPlaceholderApi
    @Provides
    @Singleton
    fun provideJSONPlaceholderApi(): JSONPlaceholderApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JSONPlaceholderApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCommentRepository(api: JSONPlaceholderApi): CommentRepository {
        return CommentRepositoryImpl(api)
    }


    // FirebaseApi
    @Provides
    @Singleton
    fun provideFirebaseAuthApi(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideUserFirebaseRepository(authApi: FirebaseAuth): UserFirebaseRepository {
        return UserFirebaseRepositoryImpl(authApi)
    }


    // Room
    @Provides
    @Singleton
    fun provideUserRoomDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            UserDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRoomRepository(db: UserDatabase): UserRoomRepository {
        return UserRoomRepositoryImpl(db.userDao)
    }

//    @Provides
//    @Singleton
//    fun provideUserRoomUseCases(repository: UserRoomRepository): UserRoomUseCases {
//        return UserRoomUseCases(
//            getUser = GetUserUseCase(repository),
//            getUsers = GetUsersUseCase(repository),
//            addUser = AddUserUseCase(repository),
//            deleteUser = DeleteUserUseCase(repository)
//        )
//    }


    // DataStore
    @Provides
    @Singleton
    fun provideUserDataStoreRepository(@ApplicationContext context: Context): UserDataStoreRepository {
        return UserDataStoreRepositoryImpl(context)
    }

}