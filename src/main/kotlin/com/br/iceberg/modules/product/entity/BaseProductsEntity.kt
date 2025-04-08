package com.br.iceberg.modules.product.entity

import com.br.iceberg.model.BaseProductsModel
import com.br.iceberg.model.TypeProducts
import com.br.iceberg.modules.product.dto.CreateNewProduct
import com.br.iceberg.modules.product.dto.UpdateProductDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
@Suppress("LongParameterList")
class BaseProductsEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(length = 100, nullable = false)
    var name: String,

    @Column(length = 255, nullable = false)
    var description: String,

    @Enumerated(EnumType.STRING)
    var type: TypeProducts,

    @Column(nullable = false)
    var isPlusProduct: Boolean = false,

    @Column(nullable = true)
    var plusPrice: Int? = null,

    @Column(nullable = false)
    var isAvailable: Boolean = false,
) {

    constructor(product: CreateNewProduct) : this(
        id = 0,
        name = product.name,
        description = product.description,
        type = product.type,
        isPlusProduct = product.isPlusProduct,
        plusPrice = product.plusPrice,
    )

    fun toModel(): BaseProductsModel {
        return BaseProductsModel(
        id = this.id,
        name = this.name,
        description = this.description,
        type = this.type,
        isPlusProduct = this.isPlusProduct,
        plusPrice = this.plusPrice,
        isAvailable = this.isAvailable,
        )
    }

    fun update(product: UpdateProductDto) {
        this.name = product.name
        this.description = product.description
        this.type = product.type
        this.isPlusProduct = product.isPlusProduct
        this.plusPrice = product.plusPrice
    }

    fun updateAvailability() {
        this.isAvailable = !this.isAvailable
    }

}