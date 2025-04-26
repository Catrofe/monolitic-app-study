package com.br.iceberg.modules.order.entity

import com.br.iceberg.model.OrderStatus
import com.br.iceberg.model.PaymentType
import com.br.iceberg.modules.order.dto.OrderDraft
import com.br.iceberg.modules.user.entity.UserEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(
    name = "orders",
    indexes = [
        Index(name = "idx_order_user", columnList = "user_id"),
        Index(name = "idx_order_status", columnList = "status")
    ]
)
@Suppress("LongParameterList")
class OrderEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: OrderStatus = OrderStatus.PENDING,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var paymentMethod: PaymentType,

    @Column(name = "total_price", nullable = false)
    var totalPrice: Int,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    val items: MutableList<OrderItemEntity> = mutableListOf(),

    @CreationTimestamp
    @Column(updatable = false)
    var createdAt: LocalDateTime,

    @UpdateTimestamp
    var updatedAt: LocalDateTime
){
    constructor(orderDraft: OrderDraft) : this(
        user = orderDraft.user,
        paymentMethod = orderDraft.paymentType,
        totalPrice = orderDraft.totalPrice,
        items = mutableListOf(),
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )

    fun addItem(item: OrderItemEntity) {
        items.add(item)
        item.order = this
    }
}