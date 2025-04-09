package com.br.iceberg.modules.cup.exception

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
class CupControllerAdvice(
    private val messageService: MessageService
) {

    @ExceptionHandler
    fun handleCupDomainException(exception: CupNotFoundException): ResponseEntity<ErrorModelReturn?> {
        val message = messageService.getMessage(exception.code, exception.args())
        val errorModelReturn = ErrorModelReturn(
            code = exception.code,
            message = message,
            args = exception.args(),
            additionalInfo = exception.additionalInfo()
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModelReturn)
    }

    @ExceptionHandler(CupNotAvailableException::class)
    fun handleCupNotAvailableException(exception: CupNotAvailableException): ResponseEntity<ErrorModelReturn?> {
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
