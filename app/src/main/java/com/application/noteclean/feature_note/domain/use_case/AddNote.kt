package com.application.noteclean.feature_note.domain.use_case

import com.application.noteclean.feature_note.domain.model.InvalidNoteException
import com.application.noteclean.feature_note.domain.model.Note
import com.application.noteclean.feature_note.domain.repository.NoteRepository

class AddNote(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException(message = "the title of the note can't be empty.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException(message = "the content of the note can't be empty.")
        }
        return noteRepository.insertNote(note)
    }
}