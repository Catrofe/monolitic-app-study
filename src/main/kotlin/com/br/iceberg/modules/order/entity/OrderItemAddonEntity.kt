package com.br.iceberg.modules.order.entity

import com.br.iceberg.model.TypeProducts
import com.br.iceberg.modules.product.entity.BaseProductsEntity
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
import jakarta.persistence.Table

@Entity
@Table(
    name = "order_item_addons",
    indexes = [
        Index(name = "idx_order_item_addon_order_item", columnList = "order_item_id"),
        Index(name = "idx_order_item_addon_product", columnList = "product_id"),
        Index(name = "idx_order_item_addon_category", columnList = "category")
    ]
)
class OrderItemAddonEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id", nullable = false)
    var orderItem: OrderItemEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    var product: BaseProductsEntity,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var category: TypeProducts,

    @Column(name = "extra_price", nullable = true)
    var extraPrice: Int? = null,
)