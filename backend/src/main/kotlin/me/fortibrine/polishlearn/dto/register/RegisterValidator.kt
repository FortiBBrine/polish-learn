package me.fortibrine.polishlearn.dto.register

import me.fortibrine.polishlearn.model.User
import me.fortibrine.polishlearn.service.UserService
import org.springframework.security.core.context.SecurityContextHolder
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

        if (SecurityContextHolder.getContext().authentication.principal is User) {
            errors.rejectValue("username", "", "You are already authenticated")
            return
        }

        if (userService.existsByName(payload.username)) {
            errors.rejectValue("username", "", "Already exists")
        }

    }

}