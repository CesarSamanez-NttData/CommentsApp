package com.csamanez.nttdata.comments_app.data.remote.dto

import com.csamanez.nttdata.comments_app.domain.model.Comment

data class CommentDto(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)

fun CommentDto.toComment(): Comment {
    return Comment(
        postId = postId,
        id = id,
        name = name,
        email = email,
        body = body
    )
}
