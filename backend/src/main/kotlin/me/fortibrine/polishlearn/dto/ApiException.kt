package me.fortibrine.polishlearn.dto

import org.springframework.web.server.ResponseStatusException

class ApiException(code: Int, message: String) : ResponseStatusException(code, message, null)
