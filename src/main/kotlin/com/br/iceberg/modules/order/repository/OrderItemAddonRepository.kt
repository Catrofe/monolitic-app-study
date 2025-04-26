package com.br.iceberg.modules.order.repository

import com.br.iceberg.modules.order.entity.OrderItemAddonEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemAddonRepository: JpaRepository<OrderItemAddonEntity, Long> {
    fun findByOrderItemId(orderItemId: Long): List<OrderItemAddonEntity>
}