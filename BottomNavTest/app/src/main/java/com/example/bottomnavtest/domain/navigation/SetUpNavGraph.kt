package com.example.bottomnavtest.domain.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bottomnavtest.Screen
import com.example.bottomnavtest.domain.data.database.TimeTableState
import com.example.bottomnavtest.presentation.attendance.AttendanceForm
import com.example.bottomnavtest.presentation.marks.MarksForm
import com.example.bottomnavtest.presentation.filters.CoursesFormView
import com.example.bottomnavtest.presentation.login.LoginFormView
import com.example.bottomnavtest.presentation.registration.RegistrationFormView
import com.example.bottomnavtest.presentation.time_table.TimeTableFormView
import com.example.bottomnavtest.viewmodels.TimeTableViewModel

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    timeTableViewModel: TimeTableViewModel,
    state: TimeTableState
) {
    NavHost(
        navController = navController,
        startDestination = "time"
    ){
        navigation(
            route = "auth",
            startDestination = Screen.Registration.route
        ){
            composable(
                route = Screen.Registration.route
            ){
                RegistrationFormView(navController = navController)
            }
            composable(
                route = Screen.Login.route
            ){
                LoginFormView(navController = navController)
            }
        }
        navigation(
            route = "time",
            startDestination = Screen.TimeTable.route
        ){
            composable(
                route = Screen.Courses.route
            ){
                CoursesFormView(navController = navController)
            }
            composable(
                route = Screen.TimeTable.route
            ){
                TimeTableFormView(navController = navController, timeTableViewModel)
            }
        }
        composable(
            route = Screen.Marks.route
        ){
            MarksForm()
        }
        composable(
            route = Screen.Attendance.route
        ){
            AttendanceForm()
        }
    }
}