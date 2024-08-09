package me.fortibrine.polishlearn.repository

import me.fortibrine.polishlearn.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: MongoRepository<User, String> {

    fun findByUsername(username: String): User?
    fun existsByUsername(username: String): Boolean
    fun findByEmail(email: String): User?
//    fun existByUsername(username: String): Boolean
//    fun existByEmail(email: String): Boolean

}