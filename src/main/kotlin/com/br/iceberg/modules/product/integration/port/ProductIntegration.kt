package com.br.iceberg.modules.product.integration.port

import com.br.iceberg.modules.product.entity.BaseProductsEntity

interface ProductIntegration {
    fun isValidProduct(product: Long): BaseProductsEntity?
    fun getToppingsOrSyrups(products: List<Long>): List<BaseProductsEntity>
    fun getPlusItems(products: List<Long>): List<BaseProductsEntity>
}