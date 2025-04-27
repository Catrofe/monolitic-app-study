package com.br.iceberg.modules.order.integration.port

import com.br.iceberg.modules.product.entity.BaseProductsEntity

interface ProductIntegrationOrder {
    fun isValidProduct(product: Long): BaseProductsEntity?
    fun getToppingsOrSyrups(product: List<Long>): List<BaseProductsEntity>
    fun getPlusItems(product: List<Long>): List<BaseProductsEntity>
}