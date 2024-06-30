package me.fortibrine.polishlearn.exception

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.security.SignatureException
import org.springframework.http.HttpStatusCode
import org.springframework.http.ProblemDetail
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AccountStatusException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun handleSecurityException(exception: Exception): ProblemDetail {
        var errorDetail: ProblemDetail? = null

        // TODO send this stack trace to an observability tool
        exception.printStackTrace()

        errorDetail = when (exception) {
            is BadCredentialsException -> {
                ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.message)
                    .apply { setProperty("description", "The username or password is incorrect") }
            }
            is AccountStatusException -> {
                ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.message)
                    .apply { setProperty("description", "The account is locked") }
            }
            is AccessDeniedException -> {
                ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.message)
                    .apply { setProperty("description", "You are not authorized to access this resource") }
            }
            is SignatureException -> {
                ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.message)
                    .apply { setProperty("description", "The JWT signature is invalid") }
            }
            is ExpiredJwtException -> {
                ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.message)
                    .apply { setProperty("description", "The JWT token has expired") }
            }
            else -> {
                ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.message)
                    .apply { setProperty("description", "Unknown internal server error.") }
            }
        }

        return errorDetail
    }
}