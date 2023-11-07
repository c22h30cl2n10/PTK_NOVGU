package com.example.bottomnavtest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.bottomnavtest.domain.data.database.TimeTableDatabase
import com.example.bottomnavtest.domain.navigation.BottomNavigationBar
import com.example.bottomnavtest.domain.navigation.SetUpNavGraph
import com.example.bottomnavtest.ui.theme.BottomNavTestTheme
import com.example.bottomnavtest.viewmodels.TimeTableViewModel

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TimeTableDatabase::class.java,
            "timetable_database.db"
        ).build()
    }
    private val viewModel by viewModels<TimeTableViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TimeTableViewModel(db.timeTableDao, db.auditoriumsDao, db.groupsDao, db.teachersDao) as T
                }
            }
        }
    )

    lateinit var navHostController: NavHostController

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val state by viewModel.timeTableState.collectAsState()
            BottomNavTestTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController)
                    },
                    content = {
                        Box() {
                            SetUpNavGraph(navController = navController, viewModel, state)
                        }
                    }
                )
            }
        }
    }
}