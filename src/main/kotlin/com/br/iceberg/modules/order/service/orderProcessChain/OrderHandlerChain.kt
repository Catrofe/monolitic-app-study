package com.br.iceberg.modules.order.service.orderProcessChain

import com.br.iceberg.modules.order.dto.OrderProcessingContext

interface OrderHandlerChain {
    fun handle(context: OrderProcessingContext): OrderProcessingContext
}