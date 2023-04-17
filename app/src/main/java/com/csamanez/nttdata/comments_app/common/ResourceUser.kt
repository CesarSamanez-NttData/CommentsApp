package com.csamanez.nttdata.comments_app.common

sealed class ResourceUser<T>(
    val status: T? = null,
    val message: String? = null
) {
    class Loading<T>(status: T? = null) : ResourceUser<T>(status)

    class Success<T>(status: T) : ResourceUser<T>(status)

    class Error<T>(message: String, status: T? = null) : ResourceUser<T>(status, message)
}