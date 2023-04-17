package com.csamanez.nttdata.comments_app.presentation.comments.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csamanez.nttdata.comments_app.domain.model.Comment

@Composable
fun CommentListItem(
    comment: Comment,
    onItemClick: (Comment) -> Unit
) {

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Comment #${comment.id}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "${comment.name}",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Body: ${comment.body}",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "${comment.email}",
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(bottom = 8.dp)
            )

        }
    }
}