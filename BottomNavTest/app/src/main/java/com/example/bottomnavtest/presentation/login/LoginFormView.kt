package com.example.bottomnavtest.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bottomnavtest.Screen

@Composable
fun LoginFormView(
    navController: NavController
) {
    Text(
        text = "LoginFormView",
        fontSize = 32.sp,
        modifier = Modifier.clickable {
            navController.navigate(Screen.Groups.route)
        }
    )
}