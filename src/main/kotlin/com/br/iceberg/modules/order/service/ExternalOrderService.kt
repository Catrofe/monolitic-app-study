package com.br.iceberg.modules.order.service

import com.br.iceberg.model.OrderModel
import com.br.iceberg.modules.order.dto.CreateOrderDTO
import com.br.iceberg.modules.order.dto.OrderProcessingContext
import com.br.iceberg.modules.order.entity.OrderEntity
import com.br.iceberg.modules.order.repository.OrderItemAddonRepository
import com.br.iceberg.modules.order.repository.OrderItemRepository
import com.br.iceberg.modules.order.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class ExternalOrderService(
    private val orderProcessingChain: OrderProcessingChain,
    private val orderRepository: OrderRepository
) {
    fun createOrder(order: CreateOrderDTO, userLogged: Long) {
        val orderContext = OrderProcessingContext(
            request = order,
            userId = userLogged
        )
        val orderProcessingContext = orderProcessingChain.execute(orderContext)
//        val orderEntity = orderProcessingContext.draft?.let { OrderEntity(it) }
        return
    }
}