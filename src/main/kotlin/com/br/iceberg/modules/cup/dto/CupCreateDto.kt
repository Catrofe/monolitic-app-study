package com.br.iceberg.modules.cup.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CupCreateDto(

    @field:NotBlank
    val name: String,

    @field:NotBlank
    val description: String,

    @field:NotNull
    val volumeMl: Double,

    @field:NotNull
    val price: Int,

    @field:NotNull
    val maxToppings: Int,

    @field:NotNull
    val maxSyrups: Int,

    @field:NotNull
    val allowExtraToppings: Boolean,

    @field:NotNull
    val allowExtraSyrups: Boolean,

    @field:NotNull
    val toppingExtraPrice: Int,

    @field:NotNull
    val syrupExtraPrice: Int,
)