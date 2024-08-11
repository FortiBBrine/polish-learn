package me.fortibrine.polishlearn.controller

import jakarta.validation.Valid
import me.fortibrine.polishlearn.dto.*
import me.fortibrine.polishlearn.dto.login.LoginDto
import me.fortibrine.polishlearn.dto.login.LoginValidator
import me.fortibrine.polishlearn.dto.register.RegisterDto
import me.fortibrine.polishlearn.dto.register.RegisterValidator
import me.fortibrine.polishlearn.model.User
import me.fortibrine.polishlearn.service.HashService
import me.fortibrine.polishlearn.service.TokenService
import me.fortibrine.polishlearn.service.UserService
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class AuthController (
    private val hashService: HashService,
    private val tokenService: TokenService,
    private val userService: UserService,
    private val loginValidator: LoginValidator,
    private val registerValidator: RegisterValidator
) {

    @PostMapping("/login")
    fun login(
        @Valid
        @RequestBody
        payload: LoginDto,

        bindingResult: BindingResult
    ): LoginResponseDto {

        loginValidator.validate(payload, bindingResult)

        if (bindingResult.hasErrors()) {
            val errorMessage = StringBuilder()
            bindingResult.allErrors.forEach { error ->
                errorMessage.append(error.defaultMessage).append("; ")
            }
            throw ApiException(400, "$errorMessage")
        }

        val user = userService.findByUsername(payload.username) as User

        return LoginResponseDto(
            token = tokenService.createToken(user),
        )
    }

    @PostMapping("/register")
    fun register(
        @Valid
        @RequestBody
        payload: RegisterDto,

        bindingResult: BindingResult
    ): LoginResponseDto {

        registerValidator.validate(payload, bindingResult)

        if (bindingResult.hasErrors()) {
            val errorMessage = StringBuilder()
            bindingResult.allErrors.forEach { error ->
                errorMessage.append(error.defaultMessage).append("; ")
            }
            throw ApiException(400, "$errorMessage")
        }

        val user = User(
            email = payload.email,
            username = payload.username,
            password = hashService.hashBcrypt(payload.password),
        )

        val savedUser = userService.save(user)

        return LoginResponseDto(
            token = tokenService.createToken(savedUser),
        )
    }

}