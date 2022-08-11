package com.application.noteclean.feature_note.data.data_source.local

import androidx.room.*
import com.application.noteclean.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM NOTE")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM NOTE WHERE ID = :noteId")
    fun getNoteById(noteId: Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Delete
    suspend fun removeNote(note: Note)

}