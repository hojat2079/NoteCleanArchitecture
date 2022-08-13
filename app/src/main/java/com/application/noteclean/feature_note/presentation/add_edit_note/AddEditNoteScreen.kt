package com.application.noteclean.feature_note.presentation.add_edit_note

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.application.noteclean.feature_note.domain.model.Note
import com.application.noteclean.feature_note.presentation.add_edit_note.component.TransparentTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val titleState = viewModel.titleState.value
    val contentState = viewModel.contentState.value

    val scaffoldState = rememberScaffoldState()

    val scopeState = rememberCoroutineScope()

    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(
                if (noteColor != -1)
                    noteColor
                else
                    viewModel.colorState.value
            )
        )
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditNoteViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditNoteViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditNoteEvent.SaveNote)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save Note"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Note.noteColors.forEach { colorItem ->
                    val colorInt = colorItem.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(colorItem)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.colorState.value == colorInt)
                                    Color.Black
                                else Color.Transparent
                            )
                            .clickable {
                                scopeState.launch {
                                    noteBackgroundAnimatable.animateTo(
                                        targetValue = colorItem,
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                            }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    TransparentTextField(
                        text = titleState.text,
                        hint = titleState.hint,
                        onValueChanged = {
                            viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                        },
                        onFocusedChanged = {
                            viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                        },
                        isHintVisible = titleState.isHintVisible,
                        singleLine = true,
                        textStyle = MaterialTheme.typography.h5
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TransparentTextField(
                        text = contentState.text,
                        hint = contentState.hint,
                        onValueChanged = {
                            viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                        },
                        onFocusedChanged = {
                            viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                        },
                        isHintVisible = contentState.isHintVisible,
                        textStyle = MaterialTheme.typography.body1,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
            }
        }
    }

}