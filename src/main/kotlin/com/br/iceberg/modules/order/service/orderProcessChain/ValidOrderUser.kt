package com.br.iceberg.modules.order.service.orderProcessChain

import com.br.iceberg.modules.order.dto.OrderProcessingContext
import com.br.iceberg.modules.order.integration.port.FindUserByIdOrder
import org.springframework.stereotype.Service

@Service
class ValidOrderUser(
    private val findUserById: FindUserByIdOrder,
) : OrderHandlerChain {

    override fun handle(context: OrderProcessingContext) =
        findUserById.findUserById(context.userId).let { user ->
            if (user == null) {
                context.errors.add("User not found: ${context.userId}")
            } else {
                context.draft.user = user
            }
            context
        }
}