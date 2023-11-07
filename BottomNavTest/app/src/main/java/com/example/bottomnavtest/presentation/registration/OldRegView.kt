package com.example.bottomnavtest.presentation.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bottomnavtest.InputTextField
import com.example.bottomnavtest.viewmodels.ValidationViewModel
import com.example.bottomnavtest.R
import com.example.bottomnavtest.Screen

@Composable
fun OldRegView(
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
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = state.email,
            onValueChange = {
                viewModel.onEvent(RegistrationFormEvents.EmailChanged(it))
            },
            isError = state.emailErrorMessage != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )
        if (state.emailErrorMessage != null) {
            Text(
                text = state.emailErrorMessage,
                color = colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = state.password,
            onValueChange = {
                viewModel.onEvent(RegistrationFormEvents.PasswordChanged(it))
            },
            isError = state.passwordErrorMessage != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        if (state.passwordErrorMessage != null) {
            Text(
                text = state.passwordErrorMessage,
                color = colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = state.repeatedPassword,
            onValueChange = {
                viewModel.onEvent(RegistrationFormEvents.RepeatedPasswordChanged(it))
            },
            isError = state.repeatedPasswordError != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Repeat password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        if (state.repeatedPasswordError != null) {
            Text(
                text = state.repeatedPasswordError,
                color = colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = state.acceptedTerms,
                onCheckedChange = {
                    viewModel.onEvent(RegistrationFormEvents.AcceptTerms(it))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Accept terms")
        }
        if (state.termsError != null) {
            Text(
                text = state.termsError,
                color = colorScheme.error,
            )
        }

        Button(
            onClick = {
                viewModel.onEvent(RegistrationFormEvents.Submit)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Submit")
        }
    }
}