package com.br.iceberg.modules.order.service.orderProcessChain

import com.br.iceberg.modules.order.dto.OrderProcessingContext
import org.springframework.stereotype.Service

@Service
class CalculateOrder: OrderHandlerChain {
    override fun handle(context: OrderProcessingContext): OrderProcessingContext {
        context.draft.totalPrice = context.draft.items.sumOf { it.calculatedPrice }
        return context
    }
}