package com.application.noteclean.feature_note.presentation.list_note.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.application.noteclean.feature_note.domain.util.NoteOrder
import com.application.noteclean.feature_note.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.ASCENDING),
    onOrderChange: (NoteOrder) -> Unit
) {
    Column(modifier = modifier) {

        Row(modifier = Modifier.fillMaxWidth()) {

            DefaultRadioButton(
                text = "Title",
                checked = noteOrder is NoteOrder.Title,
                onCheck = { onOrderChange(NoteOrder.Title(noteOrder.orderType)) }
            )
            DefaultRadioButton(
                text = "Date",
                checked = noteOrder is NoteOrder.Date,
                onCheck = { onOrderChange(NoteOrder.Date(noteOrder.orderType)) }
            )
            DefaultRadioButton(
                text = "Color",
                checked = noteOrder is NoteOrder.Color,
                onCheck = { onOrderChange(NoteOrder.Color(noteOrder.orderType)) }
            )

        }

        Spacer(modifier = Modifier.width(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {

            DefaultRadioButton(
                text = "Ascending",
                checked = noteOrder.orderType == OrderType.ASCENDING,
                onCheck = {
                    onOrderChange(
                        noteOrder.copy(OrderType.ASCENDING)
                    )
                }
            )
            DefaultRadioButton(
                text = "Descending",
                checked = noteOrder.orderType == OrderType.DESCENDING,
                onCheck = {
                    onOrderChange(
                        noteOrder.copy(OrderType.DESCENDING)
                    )
                }
            )

        }
    }
}