package com.example.bottomnavtest.presentation.registration

data class RegistrationFormState(
    val email: String = "",
    val emailErrorMessage: String? = null,
    val password: String = "",
    val passwordErrorMessage: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,
    val acceptedTerms: Boolean = false,
    val termsError: String? = null
)