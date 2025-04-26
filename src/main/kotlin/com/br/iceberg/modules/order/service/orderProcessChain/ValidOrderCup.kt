package com.br.iceberg.modules.order.service.orderProcessChain

import com.br.iceberg.modules.order.dto.OrderProcessingContext
import com.br.iceberg.modules.order.integration.port.ValidCupExists
import org.springframework.stereotype.Service

@Service
class ValidOrderCup(
    private val validCupExists: ValidCupExists,
): OrderHandlerChain{

    override fun handle(context: OrderProcessingContext): OrderProcessingContext {
        for (cup in context.request.items) {
            if (!validCupExists.isValidCup(cup.cupSizeId)) {
                context.isValid = false
                context.errors.add("Invalid cup size: ${cup.cupSizeId}")

            }
        }
        return context
    }
}