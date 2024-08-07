package me.fortibrine.polishlearn.repository

import me.fortibrine.polishlearn.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: MongoRepository<User, String> {

    fun findByEmail(email: String): Optional<User>

}