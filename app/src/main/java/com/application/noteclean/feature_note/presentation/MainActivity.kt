package com.application.noteclean.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.application.noteclean.feature_note.presentation.theme.NoteCleanTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteCleanTheme {
                // A surface container using the 'background' color from the theme

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}