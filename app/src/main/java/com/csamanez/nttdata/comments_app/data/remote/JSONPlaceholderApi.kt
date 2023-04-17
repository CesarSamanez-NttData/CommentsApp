package com.csamanez.nttdata.comments_app.data.remote

import com.csamanez.nttdata.comments_app.data.remote.dto.CommentDto
import retrofit2.http.GET

interface JSONPlaceholderApi {

    @GET("/comments/")
    suspend fun getComments(): List<CommentDto>

}