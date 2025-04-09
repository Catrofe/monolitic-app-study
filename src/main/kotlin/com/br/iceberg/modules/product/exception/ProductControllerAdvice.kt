package com.br.iceberg.modules.product.exception

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
class ProductControllerAdvice(
    val messageService: MessageService
) {

    @ExceptionHandler
    fun handleProductDomainException(exception: ProductNotFoundException): ResponseEntity<ErrorModelReturn> {
        val message = messageService.getMessage(exception.code, exception.args())
        val error = ErrorModelReturn(
            code = exception.code,
            message = message,
            args = exception.args(),
            additionalInfo = exception.additionalInfo()
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error)
    }

    @ExceptionHandler
    fun handleProductDomainException(exception: ProductPlusCreateException): ResponseEntity<ErrorModelReturn> {
        val message = messageService.getMessage(exception.code, exception.args())
        val error = ErrorModelReturn(
            code = exception.code,
            message = message,
            args = exception.args(),
            additionalInfo = exception.additionalInfo()
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error)
    }

    @ExceptionHandler
    fun handleProductDomainException(exception: ProductNotAvailableException): ResponseEntity<ErrorModelReturn> {
        val message = messageService.getMessage(exception.code, exception.args())
        val error = ErrorModelReturn(
            code = exception.code,
            message = message,
            args = exception.args(),
            additionalInfo = exception.additionalInfo()
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error)
    }

}