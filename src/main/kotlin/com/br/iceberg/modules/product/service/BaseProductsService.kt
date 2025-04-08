package com.br.iceberg.modules.product.service

import com.br.iceberg.model.BaseProductsModel
import com.br.iceberg.model.PageResponse
import com.br.iceberg.modules.cup.exception.CupNotFoundException
import com.br.iceberg.modules.product.dto.CreateNewProduct
import com.br.iceberg.modules.product.dto.UpdateProductDto
import com.br.iceberg.modules.product.entity.BaseProductsEntity
import com.br.iceberg.modules.product.exception.ProductNotFoundException
import com.br.iceberg.modules.product.exception.ProductPlusCreateException
import com.br.iceberg.modules.product.repository.BaseProductsRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BaseProductsService(
    val baseProductsRepository: BaseProductsRepository
) {

    @Transactional
    fun createNewProduct(product: CreateNewProduct): BaseProductsModel {
        if (product.isPlusProduct && product.plusPrice == null) {
            throw ProductPlusCreateException()
        }
        val productEntity = BaseProductsEntity(product)
        return baseProductsRepository.save(productEntity).toModel()
    }

    @Transactional
    fun updateProduct(product: UpdateProductDto): BaseProductsModel {
        if (product.isPlusProduct && product.plusPrice == null) {
            throw ProductPlusCreateException()
        }
        val productEntity = baseProductsRepository.findById(product.id).orElseThrow(
            { ProductNotFoundException(product.id.toString()) }
        )
        productEntity.update(product)
        return baseProductsRepository.save(productEntity).toModel()
    }

    @Transactional
    fun updateProductAvailability(id: Long): BaseProductsModel {
        val productEntity = baseProductsRepository.findById(id).orElseThrow(
            { ProductNotFoundException(id.toString()) }
        )
        productEntity.updateAvailability()
        return baseProductsRepository.save(productEntity).toModel()
    }
    
    fun getAllProducts(pageable: Pageable): PageResponse<BaseProductsModel> {
        val page = baseProductsRepository.findAll(pageable).map { it.toModel() }
        return PageResponse(
            content = page.content,
            pageNumber = page.number,
            pageSize = page.size,
            totalElements = page.totalElements,
            totalPages = page.totalPages
        )
    }
    
    fun getProductById(id: Long): BaseProductsModel {
        return baseProductsRepository.findById(id).orElseThrow(
            { ProductNotFoundException(id.toString()) }
        ).toModel()
    }

    fun deleteProduct(id: Long) {
        val productEntity = baseProductsRepository.findById(id).orElseThrow(
            { ProductNotFoundException(id.toString()) }
        )
        baseProductsRepository.delete(productEntity)
    }
}
