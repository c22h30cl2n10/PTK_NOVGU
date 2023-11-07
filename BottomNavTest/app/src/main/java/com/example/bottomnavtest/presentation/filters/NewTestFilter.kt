package com.example.bottomnavtest.presentation.filters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.bottomnavtest.viewmodels.SearchViewModel

/*

@Composable
fun NewTestFilter() {
    val viewModel = viewModel<SearchViewModel>()
    val searchText by viewModel.teacherSearchText.collectAsState()
    val persons by viewModel.persons.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row {
            TimeTableFilterSelector(title = "Курсы")
            TimeTableFilterSelector(title = "Преподаватели")
            TimeTableFilterSelector(title = "Кабинеты")
        }
        TextField(
            value = searchText,
            onValueChange = viewModel::onTeacherSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Поиск")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            items(persons){ person ->
                Text(
                    text = "${person.firstName} ${person.lastName}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            }
        }
    }
}

@Composable
fun TimeTableFilterRow(){
    Row(
    ) {
        TimeTableFilterSelector(title = "Курсы")
        TimeTableFilterSelector(title = "Преподаватели")
        TimeTableFilterSelector(title = "Кабинеты")
    }
}

@Composable
fun TimeTableFilterSelector(title: String){
    Button(
        onClick = { */
/*TODO*//*
 }
    ) {
        Text(
            text = title,
            fontSize = 14.sp
        )
    }
}*/
