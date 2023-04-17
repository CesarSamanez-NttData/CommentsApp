package com.csamanez.nttdata.comments_app.data.remote.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.csamanez.nttdata.comments_app.domain.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "users_db"
    }

}