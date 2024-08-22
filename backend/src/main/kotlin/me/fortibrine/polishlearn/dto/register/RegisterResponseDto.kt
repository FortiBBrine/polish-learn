package me.fortibrine.polishlearn.dto.register

data class RegisterResponseDto(
    val result: Map<String, String?>,
    val token: String?
)
