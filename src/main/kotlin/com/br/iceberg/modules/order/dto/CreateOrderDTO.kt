package com.br.iceberg.modules.order.dto

import com.br.iceberg.model.PaymentType
import jakarta.validation.Valid

data class CreateOrderDTO(
    @field:Valid
    val items: List<OrderItemDTO>,
    @field:Valid
    val paymentType: PaymentType,
)