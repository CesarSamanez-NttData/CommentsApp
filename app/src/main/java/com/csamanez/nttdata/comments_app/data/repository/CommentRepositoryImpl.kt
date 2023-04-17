package com.csamanez.nttdata.comments_app.data.repository

import com.csamanez.nttdata.comments_app.data.remote.JSONPlaceholderApi
import com.csamanez.nttdata.comments_app.data.remote.dto.CommentDto
import com.csamanez.nttdata.comments_app.domain.repository.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val api: JSONPlaceholderApi
) : CommentRepository {

    override suspend fun getComments(): List<CommentDto> {
        return api.getComments()
    }

}