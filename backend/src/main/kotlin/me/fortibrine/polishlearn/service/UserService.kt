package me.fortibrine.polishlearn.service

import me.fortibrine.polishlearn.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository
) {

    fun allUsers() = userRepository.findAll()

}