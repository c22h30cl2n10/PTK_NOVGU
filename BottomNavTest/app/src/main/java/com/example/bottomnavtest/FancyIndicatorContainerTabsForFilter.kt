package com.example.bottomnavtest

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bottomnavtest.domain.data.database.TimeTableState
import com.example.bottomnavtest.viewmodels.SearchViewModel
import com.example.bottomnavtest.presentation.bottomsheet_content.ClassroomFilter
import com.example.bottomnavtest.presentation.bottomsheet_content.GroupsFilter
import com.example.bottomnavtest.presentation.bottomsheet_content.PrepodFilter
import com.example.bottomnavtest.presentation.time_table.TimeTableCardView
import com.example.bottomnavtest.viewmodels.TimeTableViewModel
import java.time.LocalDate
import java.util.Calendar


@Composable
fun FancyIndicatorContainerTabsForCoures(
    timeTableViewModel: TimeTableViewModel
) {
    timeTableViewModel.getCurrentDate()
    var state by remember { mutableStateOf(0) }
    val groups by timeTableViewModel.comdoGroupsState.collectAsState()
    val titles = listOf("1", "2", "3", "4")

    val indicator = @Composable { tabPositions: List<TabPosition> ->
        FancyAnimatedIndicator(tabPositions = tabPositions, selectedTabIndex = state)
    }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        ProvideTextStyle(MaterialTheme.typography.bodyMedium) {
            Text(
                text = "Выбор курса:",
                color = Color.LightGray
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Card(
            border = BorderStroke(2.dp, color = Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {
            TabRow(
                selectedTabIndex = state,
                indicator = indicator
            ) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = state == index,
                        onClick = { state = index },
                        text = {
                            Text(
                                text = title,
                                color = if (state == index) Color.White else {
                                    Color.LightGray
                                }
                            )
                        },
                        modifier = if (state == index) {
                            Modifier.background(MaterialTheme.colorScheme.primary)
                        } else {
                            Modifier
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        timeTableViewModel.getCurrentDate()
        when (state) {
            //Перед этим нужно понять как отличать какого курса группа
            //И передавать уже список групп для конкретного курса
            0 -> {
                //Переписать логику отображения на фильтрацию списка групп отталкиваясь от текущего первогокурса
                //сравниваем первую цифру у первого курса со значением в паттерне для номера курса
                //а в других - с его значением уменьшенным на разничу с первым курсом
                GroupsFilter(
                    timeTableViewModel = timeTableViewModel,
                    groups = groups.first,
                    index = state
                )
            }

            1 -> {
                GroupsFilter(
                    timeTableViewModel = timeTableViewModel,
                    groups = groups.second,
                    index = state
                )
            }

            2 -> {
                GroupsFilter(
                    timeTableViewModel = timeTableViewModel,
                    groups = groups.third,
                    index = state
                )
            }

            3 -> {
                GroupsFilter(
                    timeTableViewModel = timeTableViewModel,
                    groups = groups.fourth,
                    index = state
                )
            }
        }
    }
}

//--------------------------------------------

@Composable
fun TimeTableTabRow(
    innerPadding: PaddingValues,
    timeTableState: TimeTableState
) {
    var state by remember { mutableStateOf(0) }
    val calendar = Calendar.getInstance()
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
    val currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

    val startDay = currentDay - currentDayOfWeek + 1
    val endDay = startDay + 6

    val monthDays = (startDay..endDay).toList()
    //Переписать на словарь
    val titles = listOf(
        "ПН\n${monthDays[1]}",
        "ВТ\n${monthDays[2]}",
        "СР\n${monthDays[3]}",
        "ЧТ\n${monthDays[4]}",
        "ПТ\n${monthDays[5]}",
        "СБ\n${monthDays[6]}"
    )

    Column(
        modifier = Modifier.padding(innerPadding)
    ) {
        TabRow(
            selectedTabIndex = state
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = {
                        Text(
                            title,
                            color = if (state == index) MaterialTheme.colorScheme.primary else {
                                Color.LightGray
                            }
                        )
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (state) {
            0 -> {
                TimeTableCardView(innerPadding, timeTableState, state)
            }

            1 -> {
                TimeTableCardView(innerPadding, timeTableState, state)
            }

            2 -> {
                TimeTableCardView(innerPadding, timeTableState, state)
            }

            3 -> {
                TimeTableCardView(innerPadding, timeTableState, state)
            }

            4 -> {
                TimeTableCardView(innerPadding, timeTableState, state)
            }

            5 -> {
                TimeTableCardView(innerPadding, timeTableState, state)
            }
        }
    }
}

//---------------------------

@Composable
fun FancyIndicatorContainerTabsForFilter(
    timeTableViewModel: TimeTableViewModel
) {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Курсы", "Препода-\nватели", "Кабинеты")
    val auditoriums by timeTableViewModel.auditoriumsState.collectAsState()
    val teachers by timeTableViewModel.teachersState.collectAsState()

    val indicator = @Composable { tabPositions: List<TabPosition> ->
        FancyAnimatedIndicator(tabPositions = tabPositions, selectedTabIndex = state)
    }

    Column {
        Card(
            border = BorderStroke(2.dp, color = Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            TabRow(
                selectedTabIndex = state,
                indicator = indicator
            ) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = state == index,
                        onClick = { state = index },
                        text = {
                            Text(
                                text = title,
                                color = if (state == index) Color.White else {
                                    Color.LightGray
                                }
                            )
                        },
                        modifier = if (state == index) {
                            Modifier.background(MaterialTheme.colorScheme.primary)
                        } else {
                            Modifier
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (state) {
            0 -> {
                // Отображаем контент для вкладки "Курсы"
                FancyIndicatorContainerTabsForCoures(
                    timeTableViewModel = timeTableViewModel
                )
            }

            1 -> {
                // Отображаем контент для вкладки "Преподаватели"
                PrepodFilter(
                    timeTableViewModel = timeTableViewModel
                )
            }

            2 -> {
                // Отображаем контент для вкладки "Кабинеты"
                ClassroomFilter(
                    timeTableViewModel = timeTableViewModel
                )
            }
        }
    }
}

@Composable
fun FancyIndicator(color: Color, modifier: Modifier = Modifier) {
    // Draws a rounded rectangular with border around the Tab, with a 5.dp padding from the edges
    // Color is passed in as a parameter [color]
    Box(
        modifier
            .padding(5.dp)
            .fillMaxSize()
            .border(BorderStroke(2.dp, color), RoundedCornerShape(5.dp))
    )
}

@Composable
fun FancyAnimatedIndicator(tabPositions: List<TabPosition>, selectedTabIndex: Int) {
    val colors = listOf(
        Color.Transparent
    )
    val transition = updateTransition(selectedTabIndex)
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            // Handle directionality here, if we are moving to the right, we
            // want the right side of the indicator to move faster, if we are
            // moving to the left, we want the left side to move faster.
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 50f)
            } else {
                spring(dampingRatio = 1f, stiffness = 1000f)
            }
        }
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            // Handle directionality here, if we are moving to the right, we
            // want the right side of the indicator to move faster, if we are
            // moving to the left, we want the left side to move faster.
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1000f)
            } else {
                spring(dampingRatio = 1f, stiffness = 50f)
            }
        }
    ) {
        tabPositions[it].right
    }

    val indicatorColor by transition.animateColor {
        colors[it % colors.size]
    }

    FancyIndicator(
        // Pass the current color to the indicator
        indicatorColor,
        modifier = Modifier
            // Fill up the entire TabRow, and place the indicator at the start
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            // Apply an offset from the start to correctly position the indicator around the tab
            .offset(x = indicatorStart)
            // Make the width of the indicator follow the animated width as we move between tabs
            .width(indicatorEnd - indicatorStart)
    )
}