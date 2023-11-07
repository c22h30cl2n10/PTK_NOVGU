package com.example.bottomnavtest.presentation.bottomsheet_content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bottomnavtest.domain.data.database.Groups
import com.example.bottomnavtest.domain.data.TimeTableSortType
import com.example.bottomnavtest.domain.data.database.TimeTableEvents
import com.example.bottomnavtest.viewmodels.TimeTableViewModel


@Composable
fun GroupsFilter(
    timeTableViewModel: TimeTableViewModel,
    groups: List<Groups>,
    index: Int
) {
    Column {
        ProvideTextStyle(MaterialTheme.typography.bodyMedium) {
            Text(
                text = "Выбор группы:",
                color = Color.LightGray
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(256.dp),
            contentPadding = PaddingValues(all = 12.dp),
            content = {
                items(groups) { group ->
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
                                timeTableViewModel.ChangeGroup(group.number)
                                timeTableViewModel.timeTableTitle.value = group.number
                                timeTableViewModel.changeFilterState()
                                timeTableViewModel.onEvent(
                                    TimeTableEvents.SortTimeTable(
                                        TimeTableSortType.GROUP
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
                                        text = group.number,
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