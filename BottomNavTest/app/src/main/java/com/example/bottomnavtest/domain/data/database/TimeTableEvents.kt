package com.example.bottomnavtest.domain.data.database

import com.example.bottomnavtest.domain.data.TimeTableSortType

sealed interface TimeTableEvents{
    object SaveTimeTable:TimeTableEvents

    data class SetId(val id: Int)
    data class SetWeek(val week: Boolean)
    data class SetWeekDay(val weekDay: Int)
    data class SetSubGroup(val subGroup: String)
    data class SetGroup(val group: String)
    data class SetDiscipline(val discipline: String)
    data class SetTeacher(val teacher: String)
    data class SetAuditorium(val auditorium: String)

    object ShowDialog: TimeTableEvents
    object HideDialog: TimeTableEvents
    data class SortTimeTable(val timeTableSortType: TimeTableSortType): TimeTableEvents
    data class DeleteTimeTable(val timeTable: TimeTable): TimeTableEvents
}