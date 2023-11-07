package com.example.bottomnavtest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bottomnavtest.viewmodels.TimeTableViewModel


@Composable
fun BottomSheetTest(timeTableViewModel: TimeTableViewModel) {
    FilterButton(timeTableViewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterButton(
    timeTableViewModel: TimeTableViewModel
) {
    var skipPartiallyExpanded by remember { mutableStateOf(false) }
    var edgeToEdgeEnabled by remember { mutableStateOf(false) }
    val showFilter by timeTableViewModel.isFilterOpen.collectAsState()
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    /*Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            Modifier.toggleable(
                value = skipPartiallyExpanded,
                role = Role.Checkbox,
                onValueChange = { checked -> skipPartiallyExpanded = checked }
            )
        ) {
            Checkbox(checked = skipPartiallyExpanded, onCheckedChange = null)
            Spacer(Modifier.width(16.dp))
            Text("Skip partially expanded State")
        }
        Row(
            Modifier.toggleable(
                value = edgeToEdgeEnabled,
                role = Role.Checkbox,
                onValueChange = { checked -> edgeToEdgeEnabled = checked }
            )
        ) {
            Checkbox(checked = edgeToEdgeEnabled, onCheckedChange = null)
            Spacer(Modifier.width(16.dp))
            Text("Toggle edge to edge enabled.")
        }
        Button(onClick = {
            viewModel.changeFilterState()
        }) {
            Text(text = "Show Bottom Sheet")
        }
    }*/
    if (showFilter) {
        ModalBottomSheet(
            onDismissRequest = { timeTableViewModel.changeFilterState() },
            sheetState = bottomSheetState
        ) {
            //Кнопка скрытия bottomsheet
            /*Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = {
                        scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                openBottomSheet = false
                            }
                        }
                    }
                ) {
                    Text("Hide Bottom Sheet")
                }
            }*/
            FancyIndicatorContainerTabsForFilter(timeTableViewModel)
        }
    }
}