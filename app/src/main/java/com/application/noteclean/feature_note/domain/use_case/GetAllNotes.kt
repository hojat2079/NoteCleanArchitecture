package com.application.noteclean.feature_note.domain.use_case

import com.application.noteclean.feature_note.domain.model.Note
import com.application.noteclean.feature_note.domain.repository.NoteRepository
import com.application.noteclean.feature_note.domain.util.NoteOrder
import com.application.noteclean.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class GetAllNotes(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.ASCENDING)
    ): Flow<List<Note>> {
        return noteRepository.getNotes().map { notes ->
            when (noteOrder.orderType) {
                OrderType.ASCENDING -> {
                    when (noteOrder) {
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                        is NoteOrder.Date -> notes.sortedBy { it.timesTamp }
                        is NoteOrder.Title -> notes.sortedBy { it.title }
                    }
                }
                OrderType.DESCENDING -> {
                    when (noteOrder) {
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                        is NoteOrder.Date -> notes.sortedByDescending { it.timesTamp }
                        is NoteOrder.Title -> notes.sortedByDescending { it.title }
                    }
                }
            }
        }
    }
}