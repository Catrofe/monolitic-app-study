package com.br.iceberg.modules.order.repository

import com.br.iceberg.modules.order.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<OrderEntity, Long>