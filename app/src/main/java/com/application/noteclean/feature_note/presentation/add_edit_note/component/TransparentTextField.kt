package com.application.noteclean.feature_note.presentation.add_edit_note.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun TransparentTextField(
    text: String,
    hint: String,
    modifier: Modifier=Modifier,
    isHintVisible: Boolean = true,
    onValueChanged: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onFocusedChanged: (FocusState) -> Unit
) {
    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChanged,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusedChanged(it)
                }
        )
        if (isHintVisible) {
            Text(
                text = hint,
                style = textStyle,
                color = Color.DarkGray,
                )
        }

    }
}