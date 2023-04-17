package com.csamanez.nttdata.comments_app.common

sealed class ResourceComment<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>(data: T? = null) : ResourceComment<T>(data)

    class Success<T>(data: T) : ResourceComment<T>(data)

    class Error<T>(message: String, data: T? = null) : ResourceComment<T>(data, message)

}
