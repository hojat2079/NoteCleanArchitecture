package com.application.noteclean.feature_note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.noteclean.feature_note.domain.model.InvalidNoteException
import com.application.noteclean.feature_note.domain.model.Note
import com.application.noteclean.feature_note.domain.use_case.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val useCase: NoteUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _titleState = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter title..."
        )
    )
    val titleState: State<NoteTextFieldState> = _titleState

    private val _contentState = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter some content.."
        )
    )
    val contentState: State<NoteTextFieldState> = _contentState

    private val _colorState = mutableStateOf(Note.noteColors.random().toArgb())
    val colorState: State<Int> = _colorState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("notId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    useCase.getNote(noteId)?.also { note ->
                        currentNoteId = note.id
                        _titleState.value = titleState.value.copy(
                            text = note.title
                        )
                        _contentState.value = contentState.value.copy(
                            text = note.content
                        )
                        _colorState.value = note.color
                    }
                }
            }
        }
    }

    fun
            onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredTitle -> {
                _titleState.value = titleState.value.copy(
                    text = event.title
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _titleState.value = titleState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _titleState.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent -> {
                _contentState.value = contentState.value.copy(
                    text = event.content
                )
            }
            is AddEditNoteEvent.ChangeContentFocus -> {
                _contentState.value = contentState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _contentState.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.ChangeColor -> {
                _colorState.value = event.color
            }
            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        useCase.addNote(
                            Note(
                                title = _titleState.value.text,
                                content = _contentState.value.text,
                                color = _colorState.value,
                                timesTamp = System.currentTimeMillis(),
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (ex: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = ex.message ?: ""
                            )
                        )
                    }
                }
            }

        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }

}