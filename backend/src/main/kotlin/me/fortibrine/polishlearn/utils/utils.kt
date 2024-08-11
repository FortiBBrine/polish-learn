package me.fortibrine.polishlearn.utils

import me.fortibrine.polishlearn.dto.ApiException
import me.fortibrine.polishlearn.model.User
import org.springframework.security.core.Authentication
import org.springframework.validation.BindingResult
import org.springframework.validation.Validator

fun Authentication.toUser() = principal as User
fun BindingResult.validate(validator: Validator, payload: Any) {

    validator.validate(payload, this)

    if (this.hasErrors()) {
        val errorMessage = StringBuilder()
        this.allErrors.forEach { error ->
            errorMessage.append(error.defaultMessage).append("; ")
        }
        throw ApiException(400, "$errorMessage")
    }
}