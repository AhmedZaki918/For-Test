package com.example.fortest

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class RegisterInputTest {

    private lateinit var registerInput: RegisterInput

    @Before
    fun setupRegisterInput() {
        registerInput = RegisterInput()
    }

    @Test
    fun `validateInput() when any data is empty returns false`() {
        // act
        val result = registerInput
            .validateInput("Ali", "", "")
        // assert
        assertThat(result).isFalse()
    }


    @Test
    fun `validateInput() when password is less than 4 digit returns false`() {
        // act
        val result = registerInput
            .validateInput("Ali", "123", "123")
        // assert
        assertThat(result).isFalse()
    }


    @Test
    fun `validateInput() when password is not equal to confirmed returns false`() {
        // act
        val result = registerInput
            .validateInput("Ali", "1236", "1234")
        // assert
        assertThat(result).isFalse()
    }
}