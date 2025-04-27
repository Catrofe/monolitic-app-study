package com.br.iceberg.modules.product.repository

import com.br.iceberg.modules.product.entity.BaseProductsEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BaseProductsRepository: JpaRepository<BaseProductsEntity, Long>{

    @Query(
        """
        SELECT p FROM BaseProductsEntity p
        WHERE p.isAvailable = :isAvailable
        AND (:type IS NULL OR p.type = :type)
        AND (:isPlusProduct IS NULL OR p.isPlusProduct = :isPlusProduct)
    """
    )
    fun findAllProductsWithFilter(
        isAvailable: Boolean,
        type: String?,
        isPlusProduct: Boolean?,
        pageable: Pageable
    ): Page<BaseProductsEntity>

    @Query("SELECT p FROM BaseProductsEntity p WHERE p.id = :id AND p.isAvailable = true")
    fun findByIdAndIsAvailableTrue(id: Long): BaseProductsEntity?

    @Query("SELECT p FROM BaseProductsEntity p WHERE p.id = :product AND p.isAvailable = true")
    fun findByIdAndAvailableIsTrue(product: Long): BaseProductsEntity?

    @Query("SELECT p FROM BaseProductsEntity p WHERE p.id IN :products AND p.isAvailable = true")
    fun findAllByIdIsInAndAvailableTrue(products: List<Long>): List<BaseProductsEntity>

    @Query(
        """
        SELECT p FROM BaseProductsEntity p 
        WHERE p.id IN :products 
        AND p.isAvailable = true 
        AND p.isPlusProduct = true
        """
    )
    fun findAllByIdIsInAndAvailableTrueAndPlusProductTrue(products: List<Long>): List<BaseProductsEntity>
}