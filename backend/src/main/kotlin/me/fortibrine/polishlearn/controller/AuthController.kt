package me.fortibrine.polishlearn.controller

import me.fortibrine.polishlearn.dto.*
import me.fortibrine.polishlearn.model.User
import me.fortibrine.polishlearn.service.HashService
import me.fortibrine.polishlearn.service.TokenService
import me.fortibrine.polishlearn.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class AuthController (
    private val hashService: HashService,
    private val tokenService: TokenService,
    private val userService: UserService
) {

    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDto): LoginResponseDto {
        val user = userService.findByUsername(payload.name) ?: throw ApiException(400, "Login failed")

        if (!hashService.checkBcrypt(payload.password, user.password)) {
            throw ApiException(400, "Login failed")
        }

        return LoginResponseDto(
            token = tokenService.createToken(user),
        )
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterDto): LoginResponseDto {
        if (userService.existsByName(payload.name)) {
            throw ApiException(400, "Name already exists")
        }

        val user = User(
            email = payload.email,
            username = payload.name,
            password = hashService.hashBcrypt(payload.password),
        )

        val savedUser = userService.save(user)

        return LoginResponseDto(
            token = tokenService.createToken(savedUser),
        )
    }


}