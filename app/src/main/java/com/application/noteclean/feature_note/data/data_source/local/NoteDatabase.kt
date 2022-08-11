package com.application.noteclean.feature_note.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.application.noteclean.feature_note.data.data_source.local.NoteDatabase.Companion.DATABASE_VERSION
import com.application.noteclean.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    exportSchema = false,
    version = DATABASE_VERSION
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getDao(): NoteDao

    companion object {
        const val DATABASE_NAME = "note_db"
        const val DATABASE_VERSION = 1
    }
}