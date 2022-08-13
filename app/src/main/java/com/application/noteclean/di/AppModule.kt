package com.application.noteclean.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.application.noteclean.feature_note.data.data_source.local.NoteDao
import com.application.noteclean.feature_note.data.data_source.local.NoteDatabase
import com.application.noteclean.feature_note.data.repository.NoteRepositoryImpl
import com.application.noteclean.feature_note.domain.repository.NoteRepository
import com.application.noteclean.feature_note.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ): NoteDatabase {
        return Room.databaseBuilder(
            context, NoteDatabase::class.java, NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(
        noteDatabase: NoteDatabase
    ): NoteDao {
        return noteDatabase.getDao()
    }

    @Singleton
    @Provides
    fun provideNoteRepository(
        noteDao: NoteDao
    ): NoteRepository {
        return NoteRepositoryImpl(noteDao)
    }

    @Singleton
    @Provides
    fun provideNoteUseCase(
        noteRepository: NoteRepository
    ): NoteUseCase {
        return NoteUseCase(
            getAllNotes = GetAllNotes(noteRepository),
            deleteNote = DeleteNote(noteRepository),
            addNote = AddNote(noteRepository),
            getNote = GetNote(noteRepository)
        )
    }
}