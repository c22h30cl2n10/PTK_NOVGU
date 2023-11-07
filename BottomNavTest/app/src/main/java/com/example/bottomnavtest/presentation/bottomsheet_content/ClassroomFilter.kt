package com.example.bottomnavtest.presentation.bottomsheet_content

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bottomnavtest.domain.data.TimeTableSortType
import com.example.bottomnavtest.domain.data.database.TimeTableEvents
import com.example.bottomnavtest.viewmodels.SearchViewModel
import com.example.bottomnavtest.viewmodels.TimeTableViewModel

@Composable
fun ClassroomFilter(
    timeTableViewModel: TimeTableViewModel
) {
    val searchViewModel = viewModel<SearchViewModel>()
    val searchText by searchViewModel.classroomSearchText.collectAsState()
    val auditoriumsState by timeTableViewModel.auditoriumsState.collectAsState()
    val filteredAuditoriums = auditoriumsState.auditoriumsList.filter { auditorium ->
        searchText.isBlank() || auditorium.number.contains(searchText, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        ProvideTextStyle(MaterialTheme.typography.bodyMedium) {
            Text(
                text = "Выбор аудитории:",
                color = Color.LightGray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(size = 16.dp))
                .border(2.dp, color = Color.LightGray, shape = RoundedCornerShape(size = 16.dp)),
            placeholder = {
                Text(text = "Поиск")
            },
            singleLine = true,
            value = searchText,
            onValueChange = searchViewModel::onClassroomSearchTextChange
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(256.dp),
            contentPadding = PaddingValues(all = 12.dp),
            content = {
                items(filteredAuditoriums) { auditorium ->
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .clip(shape = RectangleShape)
                            .clickable {
                                timeTableViewModel.onEvent(
                                    TimeTableEvents.SortTimeTable(
                                        TimeTableSortType.PREPOD
                                    )
                                )
                                timeTableViewModel.ChangeClassroom(auditorium.number)
                                timeTableViewModel.timeTableTitle.value = auditorium.number
                                timeTableViewModel.changeFilterState()
                                timeTableViewModel.onEvent(
                                    TimeTableEvents.SortTimeTable(
                                        TimeTableSortType.CLASSROOM
                                    )
                                )
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.End
                            ) {
                                ProvideTextStyle(MaterialTheme.typography.bodySmall) {
                                    Text(
                                        text = auditorium.number,
                                        fontWeight = FontWeight.W400,
                                        color = Color.Gray
                                    )
                                }
                            }
                            VerticalLine(32.dp)
                        }
                    }
                }
            }
        )
    }
}