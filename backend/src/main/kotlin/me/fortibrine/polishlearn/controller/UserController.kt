package me.fortibrine.polishlearn.controller

import me.fortibrine.polishlearn.model.User
import me.fortibrine.polishlearn.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController @Autowired constructor(
    private val userRepository: UserRepository
) {

    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") id: String): User {
        return userRepository.findById(id).get()
    }

    @PostMapping("/")
    fun createUser(@RequestBody user: User): ResponseEntity.BodyBuilder {
        userRepository.save(user)
        return ResponseEntity.status(HttpStatus.OK)
    }

    @PatchMapping("/{id}")
    fun updateUser(@PathVariable("id") id: String, @RequestBody user: User): ResponseEntity.BodyBuilder {
        userRepository.save(user)
        return ResponseEntity.ok()
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: String): ResponseEntity.BodyBuilder {
        userRepository.deleteById(id)
        return ResponseEntity.ok()
    }

}