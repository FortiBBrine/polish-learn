package me.fortibrine.polishlearn.controller

import me.fortibrine.polishlearn.model.User
import me.fortibrine.polishlearn.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserController @Autowired constructor(
    private val userService: UserService
) {

    @GetMapping("/me")
    fun authenticateUser(): ResponseEntity<User> {
        val authentication = SecurityContextHolder.getContext().authentication
        val currentUser = authentication.principal as User

        return ResponseEntity.ok(currentUser)
    }

    @GetMapping("/")
    fun allUsers(): ResponseEntity<List<User>> =
        ResponseEntity.ok(userService.allUsers())

}