package com.br.iceberg.modules.user.exception

import com.br.iceberg.config.handler.MessageService
import com.br.iceberg.model.ErrorModelReturn
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class UserControllerAdvice(
    private val messageService: MessageService
) {

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(exception: UserNotFoundException): ResponseEntity<ErrorModelReturn?> {
        val message = messageService.getMessage(exception.code, exception.args())
        val errorModelReturn = ErrorModelReturn(
            code = exception.code,
            message = message,
            args = exception.args(),
            additionalInfo = exception.additionalInfo()
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModelReturn)
    }

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleUserAlreadyExistsException(exception: UserAlreadyExistsException): ResponseEntity<ErrorModelReturn?> {
        val message = messageService.getMessage(exception.code, exception.args())
        val errorModelReturn = ErrorModelReturn(
            code = exception.code,
            message = message,
            args = exception.args(),
            additionalInfo = exception.additionalInfo()
        )
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorModelReturn)
    }

    @ExceptionHandler(UserNotAuthorizedException::class)
    fun handleUserNotAuthorizedException(exception: UserNotAuthorizedException): ResponseEntity<ErrorModelReturn?> {
        val message = messageService.getMessage(exception.code, exception.args())
        val errorModelReturn = ErrorModelReturn(
            code = exception.code,
            message = message,
            args = exception.args(),
            additionalInfo = exception.additionalInfo()
        )
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorModelReturn)
    }

    @ExceptionHandler(UserBadRequestException::class)
    fun handleUserBadRequestException(exception: UserBadRequestException): ResponseEntity<ErrorModelReturn?> {
        val message = messageService.getMessage(exception.code, exception.args())
        val errorModelReturn = ErrorModelReturn(
            code = exception.code,
            message = message,
            args = exception.args(),
            additionalInfo = exception.additionalInfo()
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModelReturn)
    }
}