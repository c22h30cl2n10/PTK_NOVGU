package com.example.bottomnavtest.domain.navigation

import com.example.bottomnavtest.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Login : NavigationItem("registration_screen", R.drawable.profile, "Профиль")
    object TimeTable : NavigationItem("time_table", R.drawable.timetable, "Расписание")
    object Attendance : NavigationItem("attendance", R.drawable.attendance, "Посещаемость")
    object Marks : NavigationItem("marks", R.drawable.marks, "Оценки")
}