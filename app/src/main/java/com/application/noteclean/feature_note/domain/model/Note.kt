package com.application.noteclean.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.application.noteclean.feature_note.presentation.theme.*


@Entity
data class Note(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val timesTamp: Long,
    val color: Int
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String) : Exception()
