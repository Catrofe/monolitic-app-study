package com.br.iceberg.model

data class OrderModel(
    val id: Long,

    val userId: Long,

    val status: OrderStatus,

    val totalAmount: Int,

    val items: List<OrderItemModel>

)
