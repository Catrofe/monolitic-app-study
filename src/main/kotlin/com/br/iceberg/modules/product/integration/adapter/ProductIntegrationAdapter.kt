package com.br.iceberg.modules.product.integration.adapter

import com.br.iceberg.modules.product.entity.BaseProductsEntity
import com.br.iceberg.modules.product.integration.port.ProductIntegration
import com.br.iceberg.modules.product.repository.BaseProductsRepository
import org.springframework.stereotype.Service

@Service
class ProductIntegrationAdapter(
    private val repository: BaseProductsRepository
): ProductIntegration {
    override fun isValidProduct(product: Long): BaseProductsEntity? {
        return repository.findByIdAndAvailableIsTrue(product)
    }

    override fun getToppingsOrSyrups(products: List<Long>): List<BaseProductsEntity> {
        return repository.findAllByIdIsInAndAvailableTrue(products)
    }

    override fun getPlusItems(products: List<Long>): List<BaseProductsEntity> {
        return repository.findAllByIdIsInAndAvailableTrueAndPlusProductTrue(products)
    }
}