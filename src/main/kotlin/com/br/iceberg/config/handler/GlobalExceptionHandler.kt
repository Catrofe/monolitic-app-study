package com.br.iceberg.config.handler

import com.br.iceberg.model.ErrorModelReturn
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
class GlobalExceptionHandler(
    private val messageService: MessageService
) {

    private val logger = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler
    fun handleAuthDomainException(exception: Exception): ResponseEntity<ErrorModelReturn?> {
        val message = messageService.getMessage("ICEBERG-999", arrayOf(exception.message))
        val errorModelReturn = ErrorModelReturn(
            code = "ICEBERG-999",
            message = message,
            args = arrayOf(exception.message),
            additionalInfo = null
        )
        logger.error("Error: ${exception.message}", exception)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorModelReturn)
    }
}
