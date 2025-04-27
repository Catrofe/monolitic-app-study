package com.br.iceberg.modules.order.service.orderProcessChain

import com.br.iceberg.modules.order.dto.OrderProcessingContext
import org.springframework.stereotype.Service

@Service
class ValidOrderErrors: OrderHandlerChain {
    override fun handle(context: OrderProcessingContext): OrderProcessingContext {
        context.isValid = context.errors.isEmpty()
        return context
}
}