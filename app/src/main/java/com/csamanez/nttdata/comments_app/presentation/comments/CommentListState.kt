package com.csamanez.nttdata.comments_app.presentation.comments

import com.csamanez.nttdata.comments_app.domain.model.Comment

data class CommentListState(
    val isLoading: Boolean = false,
    val comments: List<Comment> = emptyList(),
    val error: String = ""
)