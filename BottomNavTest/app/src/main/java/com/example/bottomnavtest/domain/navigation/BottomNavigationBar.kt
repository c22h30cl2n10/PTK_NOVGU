package com.example.bottomnavtest.domain.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.TimeTable,
        NavigationItem.Attendance,
        NavigationItem.Marks,
        NavigationItem.Login
    )
    BottomAppBar(
        containerColor = colorScheme.primary,
        //contentColor = Color.Blue
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val state by remember { mutableStateOf(false) }

        items.forEach { item ->
            NavigationBarItem(
                //Переписать на такое условие, чтобы селектед менялся только если currentRoute == item.route =>
                //то есть только при наличие такого путя в списке путей нижней навигации, а не просто при смене
                //currentRoute, как сейчас
                selected = currentRoute == item.route,
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = Color.White
                    )
                },
                label = {
                    ProvideTextStyle(MaterialTheme.typography.labelSmall) {
                        Text(
                            text = item.title,
                            color = Color.White
                        )
                    }
                },
                alwaysShowLabel = state,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = colorScheme.primary,
                    selectedTextColor = Color.White
                ),
                onClick = {
                    navController.navigate(item.route){
                        /*navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route){
                                saveState = false // Тут прикол, по идее true нужно для сохранения
                            }
                        }*/
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}