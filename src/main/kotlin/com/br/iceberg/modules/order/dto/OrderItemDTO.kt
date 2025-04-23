package com.br.iceberg.modules.order.dto

data class OrderItemDTO(
    val baseProductId: Long,
    val quantity: Int = 1,
    val toppings: List<Long> = emptyList(),
    val syrups: List<Long> = emptyList(),
    val plusItems: List<Long> = emptyList()
)
