package com.csamanez.nttdata.comments_app.presentation.utils

object ValidateForm {
    fun validateRegisterForm(
        names: String,
        lastNames: String,
        email: String,
        password: String
    ): Boolean {
        if (
            names.isBlank() ||
            lastNames.isBlank() ||
            (email.isBlank() || !isValidEmail(email)) ||
            (password.isBlank() || password.length < 6)
        ) {
            return false
        }
        return true
    }

    fun validateLoginForm(
        email: String,
        password: String
    ): Boolean {
        if (
            (email.isBlank() || !isValidEmail(email)) ||
            (password.isBlank() || password.length < 6)
        ) {
            return false
        }
        return true
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")
        return emailRegex.matches(email)
    }
}