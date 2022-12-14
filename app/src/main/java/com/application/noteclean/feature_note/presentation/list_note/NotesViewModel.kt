package com.application.noteclean.feature_note.presentation.list_note

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.noteclean.feature_note.domain.model.Note
import com.application.noteclean.feature_note.domain.use_case.NoteUseCase
import com.application.noteclean.feature_note.domain.util.NoteOrder
import com.application.noteclean.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase
) : ViewModel() {

    private val _state = mutableStateOf<NotesState>(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeleteNote: Note? = null

    private var getNoteJob: Job? = null

    init {
        getAllNotes(NoteOrder.Date(OrderType.ASCENDING))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.ChangeOrder -> {
                Log.e("MainViewModel", "onEvent: ")
                Log.e("MainViewModel", "event.noteOrder::class --> ${event.noteOrder::class}")
                Log.e(
                    "MainViewModel",
                    "state.value.noteOrder::class --> ${state.value.noteOrder::class}"
                )
                Log.e(
                    "MainViewModel",
                    "event.noteOrder.orderType --> ${event.noteOrder.orderType.name}"
                )
                Log.e(
                    "MainViewModel",
                    "state.value.noteOrder.orderType --> ${state.value.noteOrder.orderType.name}"
                )
                Log.e(
                    "MainViewModel",
                    "event.noteOrder::class == state.value.noteOrder::class --> ${event.noteOrder::class == state.value.noteOrder::class}"
                )
                Log.e(
                    "MainViewModel",
                    "event.noteOrder.orderType == state.value.noteOrder.orderType --> ${event.noteOrder.orderType == state.value.noteOrder.orderType}"
                )

                if (event.noteOrder::class == state.value.noteOrder::class &&
                    event.noteOrder.orderType.name == state.value.noteOrder.orderType.name
                ) {
                    return
                }
                getAllNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCase.deleteNote(event.note)
                    recentlyDeleteNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCase.addNote(recentlyDeleteNote ?: return@launch)
                    recentlyDeleteNote = null
                }
            }
            is NotesEvent.ShowToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getAllNotes(noteOrder: NoteOrder) {
        getNoteJob?.cancel()
        getNoteJob = noteUseCase.getAllNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }.launchIn(viewModelScope)
    }
}