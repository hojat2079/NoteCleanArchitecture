package com.application.noteclean.feature_note.data.repository

import com.application.noteclean.feature_note.data.data_source.local.NoteDao
import com.application.noteclean.feature_note.domain.model.Note
import com.application.noteclean.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        return noteDao.addNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return noteDao.removeNote(note)
    }
}