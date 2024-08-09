package me.fortibrine.polishlearn.utils

import me.fortibrine.polishlearn.model.User
import org.springframework.security.core.Authentication

fun Authentication.toUser() = principal as User
