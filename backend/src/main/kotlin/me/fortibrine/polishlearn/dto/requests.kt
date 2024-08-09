package me.fortibrine.polishlearn.dto

data class LoginDto(
    val name: String,
    val password: String,
)

data class RegisterDto(
    val email: String,
    val name: String,
    val password: String,
)