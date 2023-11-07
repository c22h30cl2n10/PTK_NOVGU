package com.example.bottomnavtest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavtest.domain.data.database.Teacher
import com.example.bottomnavtest.domain.data.database.Teachers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SearchViewModel() : ViewModel() {
    val listOfTeachers = emptyList<Teachers>()
    var listOfAuditoriums = emptyList<Teacher>()

    private val _teacherSearchText = MutableStateFlow("")
    val teacherSearchText = _teacherSearchText.asStateFlow()

    private val _classroomSearchText = MutableStateFlow("")
    val classroomSearchText = _classroomSearchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _persons = MutableStateFlow(listOfTeachers)
    val persons = teacherSearchText
        .combine(_persons){ text, persons ->
            if(text.isBlank()){
                persons
            } else{
                persons.filter {
                    true
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _persons.value
        )

    fun onTeacherSearchTextChange(text: String){
        _teacherSearchText.value = text
    }

    fun onClassroomSearchTextChange(text: String){
        _classroomSearchText.value = text
    }
}