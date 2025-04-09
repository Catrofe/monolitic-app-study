package com.br.iceberg.modules.product.service

import com.br.iceberg.model.BaseProductsModel
import com.br.iceberg.model.PageResponse
import com.br.iceberg.model.TypeProducts
import com.br.iceberg.modules.product.exception.ProductNotAvailableException
import com.br.iceberg.modules.product.repository.BaseProductsRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductsUserService(
    private val productsRepository: BaseProductsRepository,
) {
    fun getAllProductsToUser(
        type: TypeProducts?,
        isPlusProduct: Boolean?,
        pageable: Pageable
    ): PageResponse<BaseProductsModel> {
        val products = productsRepository.findAllProductsWithFilter(
            isAvailable = true,
            type = type?.name,
            isPlusProduct = isPlusProduct,
            pageable = pageable
        ).map { it.toModel() }
        return PageResponse(
            content = products.content,
            pageNumber = products.number,
            pageSize = products.size,
            totalElements = products.totalElements,
            totalPages = products.totalPages
        )
    }

    fun getProduct(id: Long): BaseProductsModel {
        return productsRepository.findByIdAndIsAvailableTrue(id)?.toModel()
            ?: throw ProductNotAvailableException(id.toString())
    }
}
