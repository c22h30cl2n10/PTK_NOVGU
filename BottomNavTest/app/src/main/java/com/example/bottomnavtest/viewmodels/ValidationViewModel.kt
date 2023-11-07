package com.example.bottomnavtest.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavtest.domain.use_case.ValidateEmail
import com.example.bottomnavtest.domain.use_case.ValidatePassword
import com.example.bottomnavtest.domain.use_case.ValidateRepeatedPassword
import com.example.bottomnavtest.domain.use_case.ValidateTerms
import com.example.bottomnavtest.presentation.registration.RegistrationFormEvents
import com.example.bottomnavtest.presentation.registration.RegistrationFormState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ValidationViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword(),
    private val validateTerms: ValidateTerms = ValidateTerms()
): ViewModel() {
    var state by mutableStateOf(RegistrationFormState())

    private val validationEventChennel = Channel<ValidationEvent>()
    val validationEvents = validationEventChennel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvents){
        when(event){
            is RegistrationFormEvents.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is RegistrationFormEvents.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is RegistrationFormEvents.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }
            is RegistrationFormEvents.AcceptTerms -> {
                state = state.copy(acceptedTerms = event.isAccepted)
            }
            is RegistrationFormEvents.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val repeatedPasswordResult = validateRepeatedPassword.execute(
            state.password, state.repeatedPassword
        )
        val termsResult = validateTerms.execute(state.acceptedTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termsResult
        ).any { !it.successful }

        if (hasError){
            state = state.copy(
                emailErrorMessage = emailResult.errorMessage,
                passwordErrorMessage = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
                termsError = termsResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChennel.send(ValidationEvent.Success)
        }
    }


    sealed class ValidationEvent{
        object Success: ValidationEvent()
    }
}