package com.br.iceberg.modules.auth.exception

import com.br.iceberg.config.handler.MessageService
import com.br.iceberg.model.ErrorModelReturn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class AuthControllerAdvice(
    private val messageService: MessageService
) {

    @ExceptionHandler
    fun handleAuthDomainException(exception: AuthUserNotFoundException): ResponseEntity<ErrorModelReturn?> {
        val message = messageService.getMessage(exception.code, exception.args())
        val errorModelReturn = ErrorModelReturn(
            code = exception.code,
            message = message,
            args = exception.args(),
            additionalInfo = exception.additionalInfo()
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModelReturn)
    }

    @ExceptionHandler
    fun handleAuthDomainException(exception: AuthBadRequestLoginException): ResponseEntity<ErrorModelReturn?> {
        val message = messageService.getMessage(exception.code, exception.args())
        val errorModelReturn = ErrorModelReturn(
            code = exception.code,
            message = message,
            args = exception.args(),
            additionalInfo = exception.additionalInfo()
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModelReturn)
    }

    @ExceptionHandler
    fun handleAuthDomainException(exception: UnauthorizedIcebergException): ResponseEntity<ErrorModelReturn?> {
        val message = messageService.getMessage(exception.code, exception.args())
        val errorModelReturn = ErrorModelReturn(
            code = exception.code,
            message = message,
            args = exception.args(),
            additionalInfo = exception.additionalInfo()
        )
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorModelReturn)
    }

    @ExceptionHandler
    fun handleAuthDomainException(exception: Exception): ResponseEntity<ErrorModelReturn?> {
        val message = messageService.getMessage("ICEBERG-999", arrayOf(exception.message))
        val errorModelReturn = ErrorModelReturn(
            code = "ICEBERG-999",
            message = message,
            args = arrayOf(exception.message),
            additionalInfo = null
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorModelReturn)
    }
}