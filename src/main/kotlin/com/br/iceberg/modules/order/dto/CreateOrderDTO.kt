package com.br.iceberg.modules.order.dto

import com.br.iceberg.model.PaymentType

data class CreateOrderDTO(
    val userId: Long,
    val items: List<OrderItemDTO>,
    val paymentType: PaymentType,
)