package com.application.noteclean.feature_note.presentation.list_note

import com.application.noteclean.feature_note.domain.model.Note
import com.application.noteclean.feature_note.domain.util.NoteOrder
import com.application.noteclean.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.ASCENDING),
    val isOrderSectionVisible: Boolean = false
)
