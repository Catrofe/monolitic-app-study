package com.br.iceberg.integration.adapter

import com.br.iceberg.modules.order.integration.port.ProductIntegrationOrder
import com.br.iceberg.modules.product.entity.BaseProductsEntity
import com.br.iceberg.modules.product.integration.port.ProductIntegration
import org.springframework.stereotype.Service

@Service
class ProductIntegrationOrderAdapter(
    private val productIntegration: ProductIntegration,
): ProductIntegrationOrder{
    override fun isValidProduct(product: Long): BaseProductsEntity? {
        return productIntegration.isValidProduct(product)
    }

    override fun getToppingsOrSyrups(product: List<Long>): List<BaseProductsEntity> {
        return productIntegration.getToppingsOrSyrups(product)
    }

    override fun getPlusItems(product: List<Long>): List<BaseProductsEntity> {
        return productIntegration.getPlusItems(product)
    }
}