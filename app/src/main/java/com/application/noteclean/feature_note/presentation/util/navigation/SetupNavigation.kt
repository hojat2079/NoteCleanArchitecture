package com.application.noteclean.feature_note.presentation.util.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.application.noteclean.feature_note.presentation.util.navigation.destination.LIST_TASK_SCREEN
import com.application.noteclean.feature_note.presentation.util.navigation.destination.addEditTaskDestination
import com.application.noteclean.feature_note.presentation.util.navigation.destination.listNoteDestination

@Composable
fun SetupNavigation(
    navController: NavHostController
) {
    val destination = remember {
        Destination(
            navController = navController
        )
    }
    NavHost(
        navController = navController,
        startDestination = LIST_TASK_SCREEN
    ) {

        listNoteDestination(
            navigateToAddEditNoteScreen = destination.addEditNote
        )

        addEditTaskDestination(
            navigateToNoteScreen = destination.listNote
        )

    }
}