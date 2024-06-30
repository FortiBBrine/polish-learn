package me.fortibrine.polishlearn.service

import me.fortibrine.polishlearn.dto.LoginUserDto
import me.fortibrine.polishlearn.dto.RegisterUserDto
import me.fortibrine.polishlearn.model.User
import me.fortibrine.polishlearn.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService @Autowired constructor(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager
) {

    fun signUp(input: RegisterUserDto): User {
        val user = User()
        user.email = input.email
        user.username = input.username
        user.password = passwordEncoder.encode(input.password)

        return userRepository.save(user)
    }

    fun authenticate(input: LoginUserDto): User {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                input.email,
                input.password
            )
        )

        return userRepository.findByEmail(input.email)
            .orElseThrow()
    }

}