package com.br.iceberg.modules.product.dto

import com.br.iceberg.model.TypeProducts
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateNewProduct(

    @field:NotBlank
    val name: String,

    @field:NotBlank
    val description: String,

    @field:NotNull
    val type: TypeProducts,

    @field:NotNull
    val isPlusProduct: Boolean = false,

    val plusPrice: Int? = null,
)
