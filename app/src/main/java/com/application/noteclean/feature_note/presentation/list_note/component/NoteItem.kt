package com.application.noteclean.feature_note.presentation.list_note.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.application.noteclean.feature_note.domain.model.Note

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerRadius: Dp = 30.dp,
    onDeleteClick: () -> Unit
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = Path().apply {
                lineTo(size.width - cutCornerRadius.toPx(), 0f)
                lineTo(size.width, cutCornerRadius.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(clipPath) {

                drawRoundRect(
                    color = Color(note.color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(note.color, 0x000000, 0.2f)
                    ),
                    size = Size(
                        cutCornerRadius.toPx() + 100f, cutCornerRadius.toPx() + 100f
                    ),
                    topLeft = Offset(
                        size.width - cutCornerRadius.toPx(), -100f
                    ),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = note.title,
                maxLines = 1,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = note.content,
                maxLines = 10,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                overflow = TextOverflow.Ellipsis
            )
        }

        IconButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = onDeleteClick
        ) {
            Icon(
                imageVector =Icons.Default.Delete,
                contentDescription = "Delete button"
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
    NoteItem(
        note = Note(
            1,
            "title",
            "content",
            timesTamp = "time",
            0xffab91,
        ),
        onDeleteClick = {}
    )
}