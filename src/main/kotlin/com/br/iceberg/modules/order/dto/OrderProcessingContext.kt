package com.br.iceberg.modules.order.dto

class OrderProcessingContext(
    val request: CreateOrderDTO,
    val userId: Long,
    var draft: OrderDraft? = null,
    val errors: MutableList<String> = mutableListOf(),
    var isValid: Boolean = true,
)