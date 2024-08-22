package me.fortibrine.polishlearn.exception

import me.fortibrine.polishlearn.dto.ApiException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

//    @ExceptionHandler(ApiException::class)
    fun handleApiException(exception: ApiException): ResponseEntity<Any> {
        val message = exception.message
        return ResponseEntity
            .status(exception.statusCode)
            .body(message)
    }
}