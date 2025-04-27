package com.br.iceberg.modules.order.repository

import com.br.iceberg.modules.order.entity.OrderItemEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemRepository: JpaRepository<OrderItemEntity, Long>
