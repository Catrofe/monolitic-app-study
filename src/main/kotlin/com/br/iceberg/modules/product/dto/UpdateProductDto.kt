package com.br.iceberg.modules.product.dto

import com.br.iceberg.model.TypeProducts
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdateProductDto(

    @NotNull
    val id: Long,

    @NotBlank
    val name: String,

    @NotBlank
    val description: String,

    @NotNull
    val type: TypeProducts,

    @NotNull
    val isPlusProduct: Boolean,

    @NotNull
    val plusPrice: Int?,
)
