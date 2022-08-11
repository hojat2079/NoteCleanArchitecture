package com.application.noteclean.feature_note.domain.use_case

data class NoteUseCase(
    val getAllNotes: GetAllNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote
)