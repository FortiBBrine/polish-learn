package me.fortibrine.polishlearn.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class User(
    @field:Id
    val id: String? = null,
    val username: String = "",
    val email: String = ""
)
