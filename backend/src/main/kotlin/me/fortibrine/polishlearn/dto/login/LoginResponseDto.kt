package me.fortibrine.polishlearn.dto.login

data class LoginResponseDto(
    val result: Map<String, String?>,
    val token: String?,
)
