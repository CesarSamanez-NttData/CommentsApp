package com.csamanez.nttdata.comments_app.domain.use_cases.comments

import com.csamanez.nttdata.comments_app.common.ResourceComment
import com.csamanez.nttdata.comments_app.data.remote.dto.toComment
import com.csamanez.nttdata.comments_app.domain.model.Comment
import com.csamanez.nttdata.comments_app.domain.repository.CommentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: CommentRepository
) {

    operator fun invoke(): Flow<ResourceComment<List<Comment>>> = flow {
        try {
            emit(ResourceComment.Loading<List<Comment>>())
            val comments = repository.getComments().map { it.toComment() }
            emit(ResourceComment.Success<List<Comment>>(comments))
        } catch (e: HttpException) {
            emit(
                ResourceComment.Error<List<Comment>>(
                    e.localizedMessage ?: "An unexpected error occurred!."
                )
            )
        } catch (e: IOException) {
            emit(
                ResourceComment.Error<List<Comment>>(
                    "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }

}