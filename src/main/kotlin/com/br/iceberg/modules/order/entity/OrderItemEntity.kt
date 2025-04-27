package com.br.iceberg.modules.order.entity

import com.br.iceberg.modules.cup.entity.CupEntity
import com.br.iceberg.modules.product.entity.BaseProductsEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Suppress("LongParameterList")
@Table(
    name = "order_items",
    indexes = [
        Index(name = "idx_order_item_order", columnList = "order_id"),
        Index(name = "idx_order_item_cup_size", columnList = "cup_size_id"),
        Index(name = "idx_order_item_base_product", columnList = "base_product_id")
    ]
)
class OrderItemEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    var order: OrderEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cup_size_id", nullable = false)
    var cupSize: CupEntity,

    @Column(name = "quantity", nullable = false)
    var quantity: Int = 1,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_product_id", nullable = false)
    var baseProduct: BaseProductsEntity,

    @Column(name = "base_price", nullable = false)
    var cupBasePrice: Int,

    @Column(name = "total_price", nullable = false)
    var price: Int,

    @OneToMany(mappedBy = "orderItem", cascade = [CascadeType.ALL], orphanRemoval = true)
    val addons: MutableList<OrderItemAddonEntity> = mutableListOf()
)