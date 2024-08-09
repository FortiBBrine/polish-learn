package me.fortibrine.polishlearn.service

import me.fortibrine.polishlearn.model.User
import me.fortibrine.polishlearn.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository
) {

    fun findById(id: String): User? = userRepository.findByIdOrNull(id)
    fun findByUsername(username: String): User? = userRepository.findByUsername(username)
    fun existsByName(name: String): Boolean = userRepository.existsByUsername(name)
    fun save(user: User) = userRepository.save(user)

}