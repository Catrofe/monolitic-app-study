package com.br.iceberg.modules.order.dto

import com.br.iceberg.model.OrderStatus
import com.br.iceberg.model.PaymentType
import com.br.iceberg.model.TypeProducts
import java.time.LocalDateTime

data class OrderResponseDTO(
    val id: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val status: OrderStatus,
    val totalPrice: Int,
    val paymentMethod: PaymentType,
    val items: List<OrderItemResponseDTO>
)

data class OrderItemResponseDTO(
    val id: Long,
    val baseProductId: Long,
    val baseProductName: String,
    val quantity: Int,
    val calculatedPrice: Int,
    val cupSizeName: String,
    val cupSizeId: Long,
    val cupBasePrice: Int,
    val toppings: List<OrderItemAddonResponseDTO>,
    val syrups: List<OrderItemAddonResponseDTO>,
    val plusItems: List<OrderItemAddonResponseDTO>
)

data class OrderItemAddonResponseDTO(
    val id: Long,
    val productId: Long,
    val productName: String,
    val category: TypeProducts,
    val extraPrice: Int?
)