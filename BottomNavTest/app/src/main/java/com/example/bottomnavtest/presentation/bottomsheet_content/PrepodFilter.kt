package com.example.bottomnavtest.presentation.bottomsheet_content

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bottomnavtest.domain.data.TimeTableSortType
import com.example.bottomnavtest.domain.data.database.TimeTableEvents
import com.example.bottomnavtest.viewmodels.SearchViewModel
import com.example.bottomnavtest.viewmodels.TimeTableViewModel

@Composable
fun PrepodFilter(
    timeTableViewModel: TimeTableViewModel
) {
    val searchViewModel = viewModel<SearchViewModel>()

    val searchText by searchViewModel.teacherSearchText.collectAsState()
    val teachersState by timeTableViewModel.teachersState.collectAsState()
    val filteredTeachers = teachersState.teachersList.filter { teacher ->
        searchText.isBlank() || teacher.lastName.contains(searchText, ignoreCase = true)
                || teacher.firstName.contains(searchText, ignoreCase = true)
                || teacher.middleName.contains(searchText, ignoreCase = true)
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        ProvideTextStyle(MaterialTheme.typography.bodyMedium) {
            Text(
                text = "Выбор преподавателя:",
                color = Color.LightGray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(size = 16.dp))
                .border(2.dp, color = Color.LightGray, shape = RoundedCornerShape(size = 16.dp)),
            singleLine = true,
            value = searchText,
            onValueChange = searchViewModel::onTeacherSearchTextChange,
            placeholder = {
                Text(text = "Поиск")
            }
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(256.dp),
            contentPadding = PaddingValues(all = 12.dp),
            content = {
                items(filteredTeachers) { teacher ->
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .clip(shape = RectangleShape)
                            .clickable {
                                timeTableViewModel.onEvent(
                                    TimeTableEvents.SortTimeTable(
                                        TimeTableSortType.CLASSROOM
                                    )
                                )
                                timeTableViewModel.ChangePrepod(teacher.lastName)
                                timeTableViewModel.timeTableTitle.value = teacher.lastName
                                timeTableViewModel.changeFilterState()
                                timeTableViewModel.onEvent(
                                    TimeTableEvents.SortTimeTable(
                                        TimeTableSortType.PREPOD
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
                                        text = "${teacher.firstName} ${teacher.middleName} ${teacher.lastName}",
                                        fontWeight = FontWeight.W400,
                                        color = Color.Gray
                                    )
                                }
                            }
                            VerticalLine(56.dp)
                        }
                    }
                }
            }
        )
    }
}



@Composable
fun VerticalLine(hight: Dp){
    Canvas(
        modifier = Modifier
            .size(2.dp, hight)
    ) {
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
        drawLine(
            color = Color.Gray,
            strokeWidth = 10f,
            start = Offset(0f, 0f),
            end = Offset(0f, size.height)
        )
    }
}