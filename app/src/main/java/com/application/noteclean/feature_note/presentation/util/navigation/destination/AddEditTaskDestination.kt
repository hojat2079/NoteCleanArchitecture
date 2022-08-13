package com.application.noteclean.feature_note.presentation.util.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.application.noteclean.feature_note.presentation.add_edit_note.AddEditNoteScreen

const val ADD_EDIT_TASK_SCREEN = "addNote"

fun NavGraphBuilder.addEditTaskDestination(
    navigateToNoteScreen: () -> Unit,
) {
    composable(
        route = "$ADD_EDIT_TASK_SCREEN?noteId={noteId}&noteColor={noteColor}",
        arguments = listOf(
            navArgument("noteId") {
                type = NavType.IntType
                defaultValue = -1
            },
            navArgument("noteColor") {
                type = NavType.IntType
                defaultValue = -1
            }
        )
    ) {
        val noteColor = it.arguments?.getInt("noteColor") ?: -1;
        AddEditNoteScreen(
            navigateToNoteScreen = navigateToNoteScreen,
            noteColor = noteColor
        )
    }
}