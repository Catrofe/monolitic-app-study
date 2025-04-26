package com.br.iceberg.modules.order.dto

import com.br.iceberg.modules.product.entity.BaseProductsEntity


class OrderItemDraft(
    val baseProduct: BaseProductsEntity,
    val quantity: Int,
    val toppings: List<BaseProductsEntity>,
    val syrups: List<BaseProductsEntity>,
    val plusItems: List<BaseProductsEntity>,
    val calculatedPrice: Int
)