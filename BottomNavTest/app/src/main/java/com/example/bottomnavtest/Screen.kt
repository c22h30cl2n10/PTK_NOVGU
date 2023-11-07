package com.example.bottomnavtest

sealed class Screen(val route: String){
    object Registration: Screen(route = "registration_screen")
    object Login: Screen(route = "login_route")

    object Courses: Screen(route = "courses")
    object Groups: Screen(route = "groups")
    object TimeTable: Screen(route = "time_table")

    object Marks: Screen(route = "marks")

    object Attendance: Screen(route = "attendance")
}