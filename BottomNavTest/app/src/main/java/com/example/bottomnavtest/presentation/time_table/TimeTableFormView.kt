package com.example.bottomnavtest.presentation.time_table


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bottomnavtest.BottomSheetTest
import com.example.bottomnavtest.R
import com.example.bottomnavtest.TimeTableTabRow
import com.example.bottomnavtest.domain.data.database.TimeTable
import com.example.bottomnavtest.domain.data.database.TimeTableState
import com.example.bottomnavtest.viewmodels.TimeTableViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeTableFormView(
    navController: NavController,
    timeTableViewModel: TimeTableViewModel,
) {
    //timeTableViewModel.onEvent(TimeTableEvents.SaveTimeTable)
    val state by timeTableViewModel.timeTableState.collectAsState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    ProvideTextStyle(MaterialTheme.typography.titleMedium) {
                        Text(
                            //Да да, ошибка, как скажешь
                            text = "Рассписание ${timeTableViewModel.timeTableTitle.value}",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        timeTableViewModel.changeFilterState()
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = "Timetable filter"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorScheme.primary
                )
            )
        },
        content = { innerPadding ->
            TimeTableTabRow(innerPadding, state)
            BottomSheetTest(timeTableViewModel)
        }
    )
}

@Composable
fun TimeTableCardView(
    innerPaddingValues: PaddingValues,
    state: TimeTableState,
    index: Int
) {
    Log.d("Debug", "List size = ${state.timeTableList.size}")
    LazyColumn {
        items(state.timeTableList) { item ->
            if (item.weekDay - 1 == index) {
                TimeTableCardItemView(item)
            }
        }
    }
}

@Composable
fun TimeTableCardItemView(
    state: TimeTable
) {
    val timeRangeColor = isTimeInRange(state.startOfClasses, state.endOfClasses, state.weekDay)
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        border = BorderStroke(2.dp, color = colorScheme.primary),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(isTimeInRange(state.startOfClasses, state.endOfClasses, state.weekDay))
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                ProvideTextStyle(MaterialTheme.typography.bodySmall) {
                    Text(
                        text = state.startOfClasses,
                        color = if (timeRangeColor == colorScheme.primary) Color.White else colorScheme.primary
                    )
                    Text(
                        text = state.endOfClasses,
                        color = if (timeRangeColor == colorScheme.primary) Color.White else colorScheme.primary
                    )
                }
            }
            Card(
                modifier = Modifier
                    .clip(shape = RectangleShape)
                    .border(2.dp, color = colorScheme.primary, shape = RectangleShape)
                    .fillMaxSize()
                    .background(Color.White), //Тут тоже нужно какой-то цвет надо из переменной
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 12.dp, horizontal = 16.dp)
                ) {
                    ProvideTextStyle(MaterialTheme.typography.bodySmall) {
                        Text(
                            text = state.discipline,
                            fontWeight = FontWeight.W700
                        )
                    }
                    ProvideTextStyle(MaterialTheme.typography.bodyMedium) {
                        Text(text = state.teacher)
                        Text(
                            text = state.auditorium,
                            fontWeight = FontWeight.W700
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun isTimeInRange(startTime: String, endTime: String, weekDay: Int): Color {
    val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

    val startCalendar = Calendar.getInstance()
    startCalendar.time = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(startTime)

    val endCalendar = Calendar.getInstance()
    endCalendar.time = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(endTime)

    val currentCalendar = Calendar.getInstance()
    currentCalendar.time = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(currentTime)

    val calendar = Calendar.getInstance()
    val dayOfWeekAsNumber = calendar.get(Calendar.DAY_OF_WEEK) -1

    Log.d("Debug", "Проверка дня недели у меня: ${dayOfWeekAsNumber} в карточке: ${weekDay}")
    if(currentCalendar.after(startCalendar) && currentCalendar.before(endCalendar) && weekDay == dayOfWeekAsNumber){
        return colorScheme.primary
    }else{
        return Color.White
    }
}