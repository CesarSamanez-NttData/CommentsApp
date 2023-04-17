package com.csamanez.nttdata.comments_app.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "lastname") val lastname: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "password") val password: String?,
    @PrimaryKey(autoGenerate = true) val id: Long = 0L
)

class InvalidUserException(
    message: String
) : Exception(message)

