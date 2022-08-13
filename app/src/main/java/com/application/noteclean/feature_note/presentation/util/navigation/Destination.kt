package com.application.noteclean.feature_note.presentation.util.navigation

import androidx.navigation.NavHostController
import com.application.noteclean.feature_note.presentation.util.navigation.destination.ADD_EDIT_TASK_SCREEN

class Destination(
    val navController: NavHostController
) {
    val addEditNote: (Int, Int) -> Unit = { noteId: Int, noteColor: Int ->
        navController.navigate("$ADD_EDIT_TASK_SCREEN?noteId=$noteId&noteColor=$noteColor")
    }
    val listNote: () -> Unit = {
        navController.navigateUp()
    }
}