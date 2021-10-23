package com.example.fortest


// This class is created for purpose of unit test only
class RegisterInput {


    fun validateInput(username: String, password: String, confirmedPassword: String): Boolean {
        if (username.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty()) {
            return false

        } else if (password.count { it.isDigit() } < 4) {
            return false

        } else if (password != confirmedPassword) {
            return false
        }
        return true
    }
}