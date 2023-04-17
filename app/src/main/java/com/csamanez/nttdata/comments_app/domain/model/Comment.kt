package com.csamanez.nttdata.comments_app.domain.model

data class Comment (
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)