package com.application.noteclean.feature_note.domain.use_case

import com.application.noteclean.feature_note.domain.model.Note
import com.application.noteclean.feature_note.domain.repository.NoteRepository

class DeleteNote(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        return noteRepository.deleteNote(note);
    }
}