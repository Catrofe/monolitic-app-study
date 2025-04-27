package com.br.iceberg.modules.order.dto

import com.br.iceberg.modules.cup.entity.CupEntity
import com.br.iceberg.modules.product.entity.BaseProductsEntity

@Suppress("LongParameterList")
class OrderItemDraft(
    var cupSize: CupEntity,
    val baseProduct: BaseProductsEntity,
    val quantity: Int,
    val toppings: List<BaseProductsEntity>,
    val syrups: List<BaseProductsEntity>,
    val plusItems: List<BaseProductsEntity>,
    var calculatedPrice: Int = 0,
)