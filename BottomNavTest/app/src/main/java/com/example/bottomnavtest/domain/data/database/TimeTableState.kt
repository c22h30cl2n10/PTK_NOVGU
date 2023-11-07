package com.example.bottomnavtest.domain.data.database

import com.example.bottomnavtest.domain.data.TimeTableSortType

data class TimeTableState(
    val timeTableList: List<TimeTable> = emptyList(),
    val Id: Int = 0,
    val Week: Boolean = false,
    val WeekDay: Int = 0,
    val SubGroup: Char = '0',
    val Group: String = "",
    val Discipline: String = "",
    val Teacher: String = "",
    val Auditorium: String = "",
    val sortType: TimeTableSortType = TimeTableSortType.GROUP,
    val weekDaySort: Int = 1,
)

data class AuditoriumsState(
    val auditoriumsList: List<Auditoriums> = emptyList(),
    val Id: Int = 0,
    val Number: String = "",
)

data class TeachersState(
    val teachersList: List<Teachers> = emptyList(),
    val Id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val middleName: String = "",
)

data class GroupsState(
    val groupsList: List<Groups> = emptyList(),
    val Id: Int = 0,
    val number: String = "",
    val numberOfKours: Int = 0,
)

data class ComdoGroupsState(
    val first: List<Groups> = emptyList(),
    val second: List<Groups> = emptyList(),
    val third: List<Groups> = emptyList(),
    val fourth: List<Groups> = emptyList(),
    val number: String = "",
    val numberOfKours: Int = 0,
)