package com.br.iceberg.modules.product.dto

import com.br.iceberg.model.TypeProducts
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdateProductDto(

    @field:NotNull
    val id: Long,

    @field:NotBlank
    val name: String,

    @field:NotBlank
    val description: String,

    @field:NotNull
    val type: TypeProducts,

    @field:NotNull
    val isPlusProduct: Boolean,

    @field:NotNull
    val plusPrice: Int?,
)
