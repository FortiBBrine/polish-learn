package me.fortibrine.polishlearn.dto

import org.springframework.web.server.ResponseStatusException

class ApiException(code: Int, message: String) : ResponseStatusException(code, message, null)

data class LoginResponseDto (
    val token: String,
)

data class Message (
    val message: String,
)