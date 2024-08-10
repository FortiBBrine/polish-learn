package me.fortibrine.polishlearn.dto.login

import me.fortibrine.polishlearn.service.HashService
import me.fortibrine.polishlearn.service.UserService
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class LoginValidator (
    private val userService: UserService,
    private val hashService: HashService
): Validator {

    override fun supports(clazz: Class<*>) = LoginDto::class.java == clazz

    override fun validate(target: Any, errors: Errors) {
        val payload = target as LoginDto
        val user = userService.findByUsername(payload.name)

        if (user == null) {
            errors.rejectValue("name", "", "Wrong username or password.")
            return
        }

        if (!hashService.checkBcrypt(payload.password, user.password)) {
            errors.rejectValue("name", "", "Wrong username or password.")
            return
        }
    }

}