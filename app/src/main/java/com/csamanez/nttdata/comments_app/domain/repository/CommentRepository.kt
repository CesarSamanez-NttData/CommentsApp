package com.csamanez.nttdata.comments_app.domain.repository

import com.csamanez.nttdata.comments_app.data.remote.dto.CommentDto

interface CommentRepository {

    suspend fun getComments():List<CommentDto>

}