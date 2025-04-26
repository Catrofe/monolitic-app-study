package com.br.iceberg.modules.order.dto

import jakarta.validation.constraints.NotNull

data class OrderItemDTO(
    @field:NotNull
    val baseProductId: Long,
    @field:NotNull
    val quantity: Int = 1,
    @field:NotNull
    val cupSizeId: Long,

    val toppings: List<Long> = emptyList(),
    val syrups: List<Long> = emptyList(),
    val plusItems: List<Long> = emptyList()
)
