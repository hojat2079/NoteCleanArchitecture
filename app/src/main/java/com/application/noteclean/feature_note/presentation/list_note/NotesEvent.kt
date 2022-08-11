package com.application.noteclean.feature_note.presentation.list_note

import com.application.noteclean.feature_note.domain.model.Note
import com.application.noteclean.feature_note.domain.util.NoteOrder

sealed class NotesEvent() {
    class DeleteNote(val note: Note):NotesEvent()
    class ChangeOrder(val noteOrder: NoteOrder): NotesEvent()
    object RestoreNote: NotesEvent()
    object ShowToggleOrderSection:  NotesEvent()
}
