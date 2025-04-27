package com.br.iceberg.modules.order.service

import com.br.iceberg.modules.order.dto.CreateOrderDTO
import com.br.iceberg.modules.order.dto.OrderDraft
import com.br.iceberg.modules.order.dto.OrderProcessingContext
import com.br.iceberg.modules.order.dto.OrderResponseDTO
import com.br.iceberg.modules.order.dto.conversor.toEntity
import com.br.iceberg.modules.order.dto.conversor.toResponse
import com.br.iceberg.modules.order.exception.UnableProcessOrder
import com.br.iceberg.modules.order.repository.OrderItemAddonRepository
import com.br.iceberg.modules.order.repository.OrderItemRepository
import com.br.iceberg.modules.order.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class ExternalOrderService(
    private val orderProcessingChain: OrderProcessingChain,
    private val orderRepository: OrderRepository,
) {
    fun createOrder(order: CreateOrderDTO, userLogged: Long): OrderResponseDTO {
        val orderContext = OrderProcessingContext(
            request = order,
            userId = userLogged,
            draft = OrderDraft(
                paymentType = order.paymentType,
            ),
        )
        val orderProcessingContext = orderProcessingChain.execute(orderContext)
        if (!orderProcessingContext.isValid) {
            throw UnableProcessOrder(orderProcessingContext.errors.toTypedArray())
        }
        val preEntity = orderProcessingContext.draft.toEntity()
        val orderEntity = orderRepository.save(preEntity)
        return orderEntity.toResponse()
    }


}