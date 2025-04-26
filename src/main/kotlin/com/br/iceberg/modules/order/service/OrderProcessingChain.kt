package com.br.iceberg.modules.order.service

import com.br.iceberg.modules.order.dto.OrderProcessingContext
import com.br.iceberg.modules.order.service.orderProcessChain.OrderHandlerChain
import org.springframework.stereotype.Service

@Service
class OrderProcessingChain(
    private val handlers: List<OrderHandlerChain>
) {
    fun execute(context: OrderProcessingContext): OrderProcessingContext {
        var currentContext = context
        for (handler in handlers) {
            currentContext = handler.handle(currentContext)
            if (!currentContext.isValid) break
        }
        return currentContext
    }
}