package com.br.iceberg.modules.product.exception

import com.br.iceberg.config.handler.MessageService
import com.br.iceberg.model.ErrorModelReturn
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ProductControllerAdvice(
    val messageService: MessageService
) {

    @ExceptionHandler
    fun handleProductDomainException(exception: ProductNotFoundException): ErrorModelReturn {
        val message = messageService.getMessage(exception.code, exception.args())
        return ErrorModelReturn(
            code = exception.code,
            message = message,
            args = exception.args(),
            additionalInfo = exception.additionalInfo()
        )
    }

    @ExceptionHandler
    fun handleProductDomainException(exception: ProductPlusCreateException): ErrorModelReturn {
        val message = messageService.getMessage(exception.code, exception.args())
        return ErrorModelReturn(
            code = exception.code,
            message = message,
            args = exception.args(),
            additionalInfo = exception.additionalInfo()
        )
    }

}