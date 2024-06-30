package me.fortibrine.polishlearn.config

import me.fortibrine.polishlearn.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Configuration
open class AppConfig @Autowired constructor(
    private val userRepository: UserRepository,
) {

    @Bean
    open fun userDetailsService() = UserDetailsService { email ->
        return@UserDetailsService userRepository.findByEmail(email)
            .orElseThrow { UsernameNotFoundException("User not found") }
    }
}