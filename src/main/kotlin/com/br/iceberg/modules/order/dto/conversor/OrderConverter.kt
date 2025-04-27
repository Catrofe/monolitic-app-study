package com.br.iceberg.modules.order.dto.conversor

import com.br.iceberg.modules.order.dto.OrderResponseDTO
import com.br.iceberg.model.TypeProducts
import com.br.iceberg.modules.order.dto.OrderDraft
import com.br.iceberg.modules.order.dto.OrderItemAddonResponseDTO
import com.br.iceberg.modules.order.dto.OrderItemResponseDTO
import com.br.iceberg.modules.order.entity.OrderEntity
import com.br.iceberg.modules.order.entity.OrderItemAddonEntity
import com.br.iceberg.modules.order.entity.OrderItemEntity

fun OrderDraft.toEntity(): OrderEntity {
    val orderEntity = OrderEntity(this)
    this.items.map { itemDraft ->
        val orderItem = OrderItemEntity(
            order = orderEntity,
            cupSize = itemDraft.cupSize,
            baseProduct = itemDraft.baseProduct,
            price = itemDraft.calculatedPrice,
            addons = mutableListOf(),
            quantity = itemDraft.quantity,
            cupBasePrice = itemDraft.cupSize.price
        )

        val addons = mutableListOf<OrderItemAddonEntity>()

        itemDraft.toppings.mapIndexed { index, topping ->
            OrderItemAddonEntity(
            orderItem = orderItem,
            product = topping,
            category = TypeProducts.TOPPING,
            extraPrice = if (index >= orderItem.cupSize.maxToppings) orderItem.cupSize.toppingExtraPrice else null
        )
        }

        itemDraft.syrups.mapIndexed { index, topping ->
            OrderItemAddonEntity(
                orderItem = orderItem,
                product = topping,
                category = TypeProducts.SYRUP,
                extraPrice = if (index >= orderItem.cupSize.maxSyrups) orderItem.cupSize.syrupExtraPrice else null
            )
        }
        itemDraft.plusItems.forEach { plusItem ->
                OrderItemAddonEntity(
                    orderItem = orderItem,
                    product = plusItem,
                    category = TypeProducts.PLUS,
                    extraPrice = plusItem.plusPrice
                )
        }

        orderItem.addons.addAll(addons)
        orderItem
    }
    return orderEntity
}

fun OrderEntity.toResponse(): OrderResponseDTO {
    return OrderResponseDTO(
        id = this.id,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        status = this.status,
        totalPrice = this.totalPrice,
        paymentMethod = this.paymentMethod,
        items = this.items.map { item ->
            return@map OrderItemResponseDTO(
                id = item.id,
                baseProductId = item.baseProduct.id,
                baseProductName = item.baseProduct.name,
                quantity = item.quantity,
                calculatedPrice = item.price,
                cupSizeName = item.cupSize.name,
                cupSizeId = item.cupSize.id,
                cupBasePrice = item.cupBasePrice,
                toppings = item.addons
                    .filter { it.category == TypeProducts.TOPPING }
                    .map { it.toAddonDTO() },
                syrups = item.addons
                    .filter { it.category == TypeProducts.SYRUP }
                    .map { it.toAddonDTO() },
                plusItems = item.addons
                    .filter { it.category == TypeProducts.PLUS }
                    .map { it.toAddonDTO() }
            )
        }
    )
}

fun OrderItemAddonEntity.toAddonDTO() = OrderItemAddonResponseDTO(
    id = this.id,
    productId = this.product.id,
    productName = this.product.name,
    category = this.category,
    extraPrice = this.extraPrice
)
