package me.fortibrine.polishlearn.dto.register

import me.fortibrine.polishlearn.service.UserService
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class RegisterValidator(
    private val userService: UserService
): Validator {
    override fun supports(clazz: Class<*>) = RegisterDto::class.java == clazz

    override fun validate(target: Any, errors: Errors) {
        val payload = target as RegisterDto

        if (userService.existsByName(payload.name)) {
            errors.rejectValue("name", "", "Already exists")
        }

    }

}