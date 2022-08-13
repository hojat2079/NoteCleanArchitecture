package com.application.noteclean.feature_note.presentation.util.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.application.noteclean.feature_note.presentation.list_note.NoteListScreen

const val LIST_TASK_SCREEN = "listTask"

fun NavGraphBuilder.listNoteDestination(
    navigateToAddEditNoteScreen: (Int, Int) -> Unit,
) {
    composable(
        route = LIST_TASK_SCREEN
    ) {
        NoteListScreen(navigateToAddEditNoteScreen)
    }
}