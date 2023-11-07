package com.example.bottomnavtest.presentation.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bottomnavtest.InputTextField
import com.example.bottomnavtest.viewmodels.ValidationViewModel
import com.example.bottomnavtest.R
import com.example.bottomnavtest.Screen

@Composable
fun RegistrationFormView(
    navController: NavController
) {
    val viewModel = viewModel<ValidationViewModel>()
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationViewModel.ValidationEvent.Success -> {
                    navController.navigate(Screen.Login.route)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(5f),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.size(198.dp)
            )

            Text(
                text = stringResource(R.string.login),
                style = TextStyle(
                    color = colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Валидация для почты и пароля
        Column(
            modifier = Modifier
                .weight(5f)
        ){
            InputTextField(
                placeholder = "Email",
                state.email
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputTextField(
                placeholder = "Password",
                state.password
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.onEvent(RegistrationFormEvents.Submit)
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(2f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.primary
                ),
            ) {
                Text(
                    text = stringResource(R.string.submit),
                    color = Color.White,
                    fontSize = 28.sp
                )
            }
        }
    }
}