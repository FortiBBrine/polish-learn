package me.fortibrine.polishlearn.controller

import me.fortibrine.polishlearn.dto.LoginResponse
import me.fortibrine.polishlearn.dto.LoginUserDto
import me.fortibrine.polishlearn.dto.RegisterUserDto
import me.fortibrine.polishlearn.model.User
import me.fortibrine.polishlearn.service.AuthenticationService
import me.fortibrine.polishlearn.service.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@RestController
class AuthenticationController @Autowired constructor(
    private val jwtService: JwtService,
    private val authenticationService: AuthenticationService
) {

    @PostMapping("/signup")
    fun register(
        @RequestBody
        registerUserDto: RegisterUserDto
    ): ResponseEntity<User> {
        val registeredUser = authenticationService.signUp(registerUserDto)

        return ResponseEntity.ok(registeredUser)
    }

    @PostMapping("/login")
    fun authenticate(
        @RequestBody
        loginUserDto: LoginUserDto
    ): ResponseEntity<LoginResponse> {
        val authenticatedUser = authenticationService.authenticate(loginUserDto)
        val jwtToken = jwtService.generateToken(authenticatedUser)
        val loginResponse = LoginResponse(
            jwtToken,
            jwtService.jwtExpiration
        )

        return ResponseEntity.ok(loginResponse)
    }

}