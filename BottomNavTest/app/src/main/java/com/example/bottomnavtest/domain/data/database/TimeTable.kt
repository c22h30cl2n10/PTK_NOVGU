package com.example.bottomnavtest.domain.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timeTable")
data class TimeTable(
    @PrimaryKey
    val id: Int,
    val week: Boolean,
    val weekDay: Int,
    val subGroup: Char,
    val group: String,
    val discipline: String,
    val teacher: String,
    val auditorium: String,
    val timeTableNumber: Int,
    val startOfClasses: String,
    val endOfClasses: String,
)
